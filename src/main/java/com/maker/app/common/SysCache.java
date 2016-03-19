package com.maker.app.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 系统缓存，存放一些经常用到却又不经常改变的数据
 * @author hlz
 * @date 2015-5-8 上午10:15:22
 */
public class SysCache {
	
	private static Logger logger = Logger.getLogger(SysCache.class);
	
	public static Map<String, AppInterface> allInterfaces = Collections.synchronizedMap(new HashMap<String, AppInterface>());
	public static Map<String, Object> allServices = Collections.synchronizedMap(new HashMap<String, Object>());
	public static Map<String, String> allMethods = Collections.synchronizedMap(new HashMap<String, String>());
	
	public static void load() {
		logger.info("loading interface configure file......");
		loadInterterfaceConfig();
		logger.info("loading service configure file......");
		loadServiceConfig();
		logger.info("loading completed...");
	}
	
	@SuppressWarnings("unchecked")
	private static void loadInterterfaceConfig() {
		try {
			SAXReader reader = new SAXReader();
	        Document document = reader.read(new File(SystemConfig.getClassPath() + "interface.xml"));
	        Element root = document.getRootElement();
	        List<Element> interfaces = (List<Element>)root.elements("interface");
	        if(interfaces != null && interfaces.size() > 0) {
	            for (Element intf : interfaces) {
	            	AppInterface ai = new AppInterface();
	            	List<Parameter> parameters = new ArrayList<Parameter>();
	            	boolean needAuth = "true".equals(intf.attributeValue("need-auth"));
	            	ai.setNeedAuth(needAuth);
	            	ai.setName(intf.attributeValue("name"));
	            	logger.info(intf.attributeValue("name")+":"+needAuth);
	            	ai.setParameters(parameters);
	            	List<Element> params = ((Element)intf.elements("parameters").get(0)).elements("param");
	            	for (Element param : params) {
	            		String name = param.attributeValue("name");
	            		ParameterTypes type = ParameterTypes.valueOf(param.attributeValue("type"));
	            		boolean required = Boolean.parseBoolean(param.attributeValue("required"));
	            		String maxLength = param.attributeValue("maxLength");
	            		int maxLen = 0;
	            		if (maxLength != null && maxLength.trim().length() > 0)
	            			maxLen = Integer.parseInt(maxLength);
	            		
	            		Parameter parameter = new Parameter();
	            		parameter.setName(name);
	            		parameter.setRequired(required);
	            		parameter.setType(type);
	            		parameter.setMaxLength(maxLen);
	            		parameters.add(parameter);
	            	}
	            	allInterfaces.put(ai.getName(), ai);
	            }
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadServiceConfig() {
		try {
			SAXReader reader = new SAXReader();
	        Document document = reader.read(new File(SystemConfig.getClassPath() + "interface-service.xml"));
	        Element root = document.getRootElement();
	        List<Element> interfaces = (List<Element>)root.elements("interface");
            for (Element element : interfaces) {
            	String name = element.attributeValue("name");
            	String cls = element.attributeValue("class");
            	String method = element.attributeValue("method");
//            	allServices.put(name, ServiceFactory.getService(cls));
            	allServices.put(name, SpringService.getBean(cls));
            	allMethods.put(name, method);
            }
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

