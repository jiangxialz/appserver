package com.maker.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maker.app.dao.IAppVisitLogDAO;
import com.maker.app.entity.AppVisitLog;
import com.maker.app.service.IAppVisitLogService;

@Service
public class AppVisitLogService implements IAppVisitLogService {
	
	@Autowired
	private IAppVisitLogDAO appVisitLogDAO;

	@Override
	public int add(AppVisitLog appVisitLog) {
		return appVisitLogDAO.add(appVisitLog);
	}

	@Override
	public void addList(List<AppVisitLog> list) {
		appVisitLogDAO.addList(list);
	}

}
