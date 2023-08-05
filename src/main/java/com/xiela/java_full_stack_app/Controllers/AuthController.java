package com.xiela.java_full_stack_app.Controllers;

import com.xiela.java_full_stack_app.Model.User;
import com.xiela.java_full_stack_app.Services.AuthService;
import com.xiela.java_full_stack_app.Services.Login;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;


    public  AuthController(AuthService authService){
        this.authService =  authService;
    }


    record RegisterRequest(String first_name, String last_name, String email, String password, String password_confirm){}
    record RegisterResponse(Long id, String first_name, String last_name, String email ){}

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {

        if(!Objects.equals(registerRequest.password(), registerRequest.password_confirm())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }

        User user = authService.register(
                  registerRequest.first_name(),
                  registerRequest.last_name(),
                  registerRequest.email(), 
                  registerRequest.password(),
                  registerRequest.password_confirm());

        return new RegisterResponse (user.getId(), user.getFirst_name(),user.getLast_name(),user.getEmail());
    }


    record LoginRequest(String email, String password){}
    record LoginResponse(String token){}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        Login login =  authService.login(loginRequest.email(), loginRequest.password());
        
        Cookie cookie = new Cookie("refresh_token", login.getRefreshToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");

        response.addCookie(cookie);

        return new LoginResponse(login.getAccessToken().getToken());
    }

    record UserResponse(Long id, String first_name, String last_name, String email){}

    @GetMapping("/user")
    public UserResponse user(HttpServletRequest request){
        var user = (User) request.getAttribute("user");

        return new UserResponse(user.getId(), user.getFirst_name(), user.getLast_name(), user.getEmail());
    }


    record RefreshResponse(String token){}

    @PostMapping("/refresh")
    public RefreshResponse refresh(@CookieValue("refresh_token") String refreshToken){
        return new RefreshResponse(authService.refreshAccess(refreshToken).getAccessToken().getToken());
    }

}
