package com.sparta.springpracblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringpracblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringpracblogApplication.class, args);
	}

}
