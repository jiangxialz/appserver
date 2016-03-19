package com.maker.app.dao;

import java.util.List;

import com.maker.app.entity.AppVisitLog;

public interface IAppVisitLogDAO {
	
	public int add(AppVisitLog appVisitLog);
	
	public void addList(List<AppVisitLog> list);

}
