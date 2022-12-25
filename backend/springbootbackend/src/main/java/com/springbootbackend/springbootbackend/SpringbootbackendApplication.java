package com.springbootbackend.springbootbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;


@SpringBootApplication
@EnableKafka
public class SpringbootbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootbackendApplication.class, args);
	}
}
