package com.felipestanzani.migrationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MigrationdemoApplication {

	private MigrationdemoApplication() {}

	static void main(String[] args) {
		SpringApplication.run(MigrationdemoApplication.class, args);
	}

}
