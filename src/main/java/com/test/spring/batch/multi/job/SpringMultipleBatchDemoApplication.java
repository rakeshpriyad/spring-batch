package com.test.spring.batch.multi.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringMultipleBatchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMultipleBatchDemoApplication.class, args);
	}

}
