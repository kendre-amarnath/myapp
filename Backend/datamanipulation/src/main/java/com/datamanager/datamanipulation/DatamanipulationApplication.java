package com.datamanager.datamanipulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class DatamanipulationApplication {
	public static void main(String[] args) {
		SpringApplication.run(DatamanipulationApplication.class, args);
	}
}
