package com.wistrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WistripApplication {

	public static void main(String[] args) {
		SpringApplication.run(WistripApplication.class, args);
	}

}
