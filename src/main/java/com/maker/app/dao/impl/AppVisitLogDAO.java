package com.maker.app.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maker.app.dao.IAppVisitLogDAO;
import com.maker.app.entity.AppVisitLog;

@Repository
public class AppVisitLogDAO implements IAppVisitLogDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int add(AppVisitLog appVisitLog) {
		return sqlSession.insert("com.maker.app.mapper.AppVisitLogMapper.add", appVisitLog);
	}

	@Override
	public void addList(List<AppVisitLog> list) {
		sqlSession.insert("com.maker.app.mapper.AppVisitLogMapper.addList", list);
	}

}
