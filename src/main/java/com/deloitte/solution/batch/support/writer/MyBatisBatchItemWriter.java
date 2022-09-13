package com.deloitte.solution.batch.support.writer;

import java.util.List;

public class MyBatisBatchItemWriter<T> extends org.mybatis.spring.batch.MyBatisBatchItemWriter<T> {

	@Override
	public void write(List<? extends T> items) {
		// TODO Auto-generated method stub
		super.write(items);
	}
}
