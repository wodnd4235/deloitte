package com.deloitte.solution.batch.support.writer;

import java.util.ArrayList;
import java.util.List;

public class MyBatisBatchItemListWriter<T> extends org.mybatis.spring.batch.MyBatisBatchItemWriter<List<T>> {

	private MyBatisBatchItemWriter<T> myBatisBatchItemWriter;

	public MyBatisBatchItemListWriter(MyBatisBatchItemWriter<T> myBatisBatchItemWriter) {
		this.myBatisBatchItemWriter = myBatisBatchItemWriter;
	}

	@Override
	public void write(List<? extends List<T>> items) {
		List<T> totalList = new ArrayList<>();

		for (List<T> list : items) {
			totalList.addAll(list);
		}
		myBatisBatchItemWriter.write(totalList);
	}
}
