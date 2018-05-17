package com.soft_telecom;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SoftTelecomApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftTelecomApplication.class, args);
	}

}
