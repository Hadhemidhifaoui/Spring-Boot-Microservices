package com.example.SpringbootMicroservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMicroservice1Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(SpringBootMicroservice1Application.class, args);
		} catch (Exception e) {
			// Handle the exception
			e.printStackTrace();
			// You can log the exception or take other appropriate actions
		}
	}

}
