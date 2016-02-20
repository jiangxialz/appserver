package com.maker.app.action;


public class DataResponseVo<T> extends ResponseVo{
	
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
}
