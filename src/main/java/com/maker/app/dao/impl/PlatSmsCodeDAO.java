package com.maker.app.dao.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maker.app.constant.AppConstants;
import com.maker.app.dao.IPlatSmsCodeDAO;
import com.maker.app.entity.PlatSmsCode;

/**
 * 短信验证码数据操作类
 * @author hlz
 * @date 2015-6-4 下午5:25:25
 */
@Repository
public class PlatSmsCodeDAO implements IPlatSmsCodeDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(PlatSmsCode platSmsCode) {
		return sqlSession.insert("com.maker.app.mapper.PlatSmsCodeMapper.insert", platSmsCode);
	}
	
	/**
	 * 根据条件获取短信记录
	 */
	@Override
	public PlatSmsCode getPlatSmsCode(String mobile, String smsCode) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mobile", mobile);
		paramMap.put("smsCode", smsCode);
		paramMap.put("userId", 0); // 注册
		paramMap.put("isUse", AppConstants.USE_FALSE); // 未使用
		return sqlSession.selectOne("com.maker.app.mapper.PlatSmsCodeMapper.getPlatSmsCode", paramMap);
	}

	@Override
	public int update(PlatSmsCode platSmsCode) {
		return sqlSession.update("com.maker.app.mapper.PlatSmsCodeMapper.update",platSmsCode);
	}
	
}
