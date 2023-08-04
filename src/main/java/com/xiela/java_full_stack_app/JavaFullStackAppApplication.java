package com.xiela.java_full_stack_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class JavaFullStackAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaFullStackAppApplication.class, args);
	}

}
