package com.deloitte.solution.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired public JobBuilderFactory jobBuilderFactory;
	@Autowired public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job ExampleJob(){
		return jobBuilderFactory.get(JOB_NAME)
				.start(startStep())
				.next(nextStep())
				.build();
	}
	
	@Bean
	@JobScope
	public Step startStep() {
		return stepBuilderFactory.get(JOB_NAME)
				.tasklet((contribution, chunkContext) -> {
                    log.info("Start Step!");
                    return RepeatStatus.FINISHED;
                })
				.build();
	}
	
	@Bean
	public Step nextStep() {
		return stepBuilderFactory.get("nextStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Next Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
	}
}
