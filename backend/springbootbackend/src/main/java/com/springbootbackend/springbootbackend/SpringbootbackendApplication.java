package com.springbootbackend.springbootbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
public class SpringbootbackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootbackendApplication.class, args);
	}
}
