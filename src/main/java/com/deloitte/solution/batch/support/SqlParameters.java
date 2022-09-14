package com.deloitte.solution.batch.support;

import java.util.Map;

import org.springframework.batch.core.JobParameters;

public interface SqlParameters {

//	public Map<String,Object> setParameters(JobParameters jobParameters);

	public Map<String, Object> setParameters(JobParameters jobParameters, Map<String, Object> parameter);
}
