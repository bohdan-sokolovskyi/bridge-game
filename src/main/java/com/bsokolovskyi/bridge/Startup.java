package com.bsokolovskyi.bridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = { "com.bsokolovskyi.bridge.web.db.repository" })
@SpringBootApplication
public class Startup extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

}
