package com.deloitte.solution;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableBatchProcessing // 배치 기능 활성화
@SpringBootApplication
public class DeloitteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeloitteApplication.class, args);
	}

}
