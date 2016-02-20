package com.maker.app.service;

import java.util.List;

import com.maker.app.entity.AppVisitLog;

public interface IAppVisitLogService {
	
	public int add(AppVisitLog appVisitLog);
	
	public void addList(List<AppVisitLog> list);

}
