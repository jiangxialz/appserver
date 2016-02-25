package com.maker.app.service;

import java.util.HashMap;

import org.junit.Test;

import com.maker.app.junit.BaseJunitCase;
import com.wonder.http.HttpClientSendUtil;

public class PlatSendSmsServiceTest extends BaseJunitCase{
	
	@Test
	public void testSendSms(){
		
		String reqUrlStr = "http://localhost/appserver/front/global-external-req.3d?action=getSmsCode";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("mobile", "13632672548");
		try {
			String resultJson = HttpClientSendUtil.postRequest(reqUrlStr, params, "http");
			logger.info(resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
