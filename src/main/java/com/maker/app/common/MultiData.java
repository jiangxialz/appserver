package com.maker.app.common;

import java.util.List;

public class MultiData {
	
	private Page page;
	private List resultSet;
	private Object condition;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List getResultSet() {
		return resultSet;
	}
	public void setResultSet(List resultSet) {
		this.resultSet = resultSet;
	}
	public Object getCondition() {
		return condition;
	}
	public void setCondition(Object condition) {
		this.condition = condition;
	}
}
