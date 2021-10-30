package com.green.product2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Product2Application {

	public static void main(String[] args) {
		SpringApplication.run(Product2Application.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}

}
