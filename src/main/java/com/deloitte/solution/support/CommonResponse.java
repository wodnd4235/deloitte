package com.deloitte.solution.support;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonResponse<T> extends BasicResponse {
	private T data;
	private int count;
	
	public CommonResponse(T data) {
		this.data = data;
		if(data instanceof List) {
			this.count = ((List<?>)data).size();
		} else {
			this.count = 1;
		}
	}
}
