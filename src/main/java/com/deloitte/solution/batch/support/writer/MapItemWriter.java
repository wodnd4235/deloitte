package com.deloitte.solution.batch.support.writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemWriter;

public class MapItemWriter<T> implements ItemWriter<T> {

	private Map<String, T> writtenMapItems = new HashMap<String,T>();

	@Override
	public void write(List<? extends T> items) throws Exception {
		for (T item : items) {
			
			if(item instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, T> temp = (Map)item;
				
				temp.forEach((key,value) ->{
					writtenMapItems.put(key,value);					
				});
			}
			
		}
	}

	public Map<String, T> getWrittenItems() {
		return writtenMapItems;
	}
}
