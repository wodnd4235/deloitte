package com.deloitte.solution.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 배치설명 : API 통신 관련 배치
 **/
//@ConditionalOnProperty(name = "job.name", havingValue = ApiConfiguration.JOB_NAME)
@Slf4j
//@RequiredArgsConstructor // 생성자DI
@Configuration // 스프링설정어노테이션
@EnableBatchProcessing
public class ApiConfiguration {
	
	public static final String JOB_NAME = "apiConfiguration";
	
//	@Bean
//	public Job ExampleJob(){
//		Job exampleJob = JobBuilderFactory.get("exampleJob");
//		exampleJob.preventRestart(); // 실패해도 재시작 하지 않는다.
//		
//				.start() // 임시데이터 비어있어야 진행
//
//				.next() // 임시데이터 인서트
//				
//				.incrementer(new RunIdIncrementer()).build(); // 파라메타변경없이 실행가능
//		return exampleJob;
//	}
//	
//	@Bean
//	public step Step1() {
//		Step
//	}
}
