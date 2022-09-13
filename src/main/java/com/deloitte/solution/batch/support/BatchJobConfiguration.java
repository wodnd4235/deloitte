package com.deloitte.solution.batch.support;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음

	@Autowired
	private StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음

	private JobParameters jobParameters;

	public String mapperId(String mapperId, String sqlId) {
		return mapperId.concat(sqlId);
	}

	public JobBuilder getJobBuilderFactory(String jobName) {
		JobBuilder jobBuilder = this.jobBuilderFactory.get(jobName);
		jobBuilder.preventRestart(); // 실패해도 재시작 하지 않는다.
		jobBuilder.listener(new JobExecutionListener() {
			@Override
			public void beforeJob(JobExecution jobExecution) {
				jobParameters = jobExecution.getJobParameters();

				log.debug("JobExecutionListener.getJobParameters => {}", jobParameters);
			}

			@Override
			public void afterJob(JobExecution jobExecution) {
			}
		});
		return jobBuilder;
	}

	public StepBuilder getStepBuilderFactory(String stepName) {
		return stepBuilderFactory.get(stepName);
	}

	public JobParameters getJobParameters() {
		return jobParameters;
	}

	public void setJobParameters(JobParameters jobParameters) {
		this.jobParameters = jobParameters;
	}

}
