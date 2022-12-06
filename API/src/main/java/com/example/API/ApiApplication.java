package com.example.API;

import com.example.API.models.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	

	public static void main(String[] args) {

		JsonParser parser = new JsonParser();
		Root root = parser.parse();

		System.out.println(root.toString());

		SpringApplication.run(ApiApplication.class, args);
	}

}
