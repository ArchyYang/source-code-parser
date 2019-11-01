package com.project.fileparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})

public class FileparserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileparserApplication.class, args);
	}

}
