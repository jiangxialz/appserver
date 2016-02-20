package com.maker.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 参数配置属性文件
 * @author lz
 * 2014-11-25 下午2:10:24
 *
 */
public class OptionUtils {
	
	private static Log log = LogFactory.getLog(OptionUtils.class);
	private static long optionFileLastModifyTime = 0L;

	public static Properties options = new Properties(); 
	
	static{
		try{
			String confFileUrl = OptionUtils.class.getResource("/options.properties").getPath();
			File file = new File(confFileUrl);
			optionFileLastModifyTime = file.lastModified();
			InputStream is = new FileInputStream(file);
			options.load(is);
		}catch(Exception e){
			log.error("can not get file options.properties " + e.getMessage());
		}
	}
	
	
	public static void reload(){
		try{
			String confFileUrl = OptionUtils.class.getResource("/options.properties").getPath();
			File file = new File(confFileUrl);
			if(optionFileLastModifyTime != file.lastModified()){
				log.info("reload options.properties, file.lastModified() = " + file.lastModified());
				options.clear();
				InputStream is = new FileInputStream(file);
				options.load(is);
				optionFileLastModifyTime = file.lastModified();
			}
		}catch(Exception e){
			log.error(" can not get file options.properties " + e.getMessage());
		}
	}

	/****
	 * 获取options文件key值
	 * @param key
	 * @return
	 */
	public static String getOption(String key){
		//reload();
	    String message = options.getProperty(key);
	    return message;
	}
	
	/*****
	 * 获取options文件key值，如果为空返回默认值 defaultValue
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getDefaultOption(String key,String defaultValue){
		//reload();
	    String message=options.getProperty(key);
	    if(StringUtils.isEmpty(message)){
	    	return defaultValue;
	    }
	    return message;
	}
	
	


	/****
	 * 转换为Boolean对象（1,yes,YES,TRUE,true可以转化为true,0,no,NO,FALSE,false转化为false,其他返回false）
	 * @param key
	 * @return
	 */
	public static boolean convertToBoolean(String value){
		if(value == null){
			return false;
		}
		String mes = value.toLowerCase();
		if(mes.equals("1") || mes.equals("yes") || mes.equals("true")){
			return true;
		}
		if(mes.equals("0") || mes.equals("no") || mes.equals("false")){
			return false;
		}
	    return false;
	}

	/****
	 * 转换为Boolean对象（1,yes,YES,TRUE,true可以转化为true,0,no,NO,FALSE,false转化为false,其他返回默认值）
	 * @param key
	 * @return
	 */
	public static boolean convertToBoolean(String value,boolean defaultValue){
		if(value == null){
			return defaultValue;
		}
		String mes = value.toLowerCase();
		if(mes.equals("1") || mes.equals("yes") || mes.equals("true")){
			return true;
		}
		if(mes.equals("0") || mes.equals("no") || mes.equals("false")){
			return false;
		}
	    return defaultValue;
	}
	
	
	/****
	 * 获取配置文件key值,并转换为Integer对象
	 * @param key
	 * @return
	 */
	public static Integer convertToInteger(String value) throws Exception{
		if(value == null){
			return null;
		}
		try{
			Integer o = Integer.valueOf(value);
			return o;
		}catch(Exception e){
			log.error("error fomart String to Integer, the message = " + value);
			throw new Exception("error fomart String to Integer, the message = " + value);
		}
	}
	
	
	public static String replaceMessage(String message,String key,String replacement){
		if(StringUtils.isEmpty(message)){
			return null;
		}
		if(StringUtils.isEmpty(key)){
			return message;
		}
		if(StringUtils.isEmpty(replacement)){
			return message;
		}
		return message.replaceAll(key, replacement);
	}

	
	/****
	 * 替换{0}{1}等类型
	 * @param message
	 * @param replacement
	 * @return
	 */
	public static String replaceMessage(String message,List<String> replacement){
		if(StringUtils.isEmpty(message)){
			return null;
		}
		if(replacement == null){
			return message;
		}
		Object[] rro = replacement.toArray();
		return String.format(message, rro);
	}
	
	
	public static void main(String[] args) {
		String result = OptionUtils.getDefaultOption("sysconfig.debugMode","false");
		System.out.println(result);
	}

}

/***
 * 自动从新加载文件
 * @author: amin
 * @desc 是否启用定时器扫描还是使用quartz调度.
 * 暂时每次读取就重新加载(效率肯定低)
 */
/*class ReloadOptions extends Thread{

	@Override
	public void run() {
		super.run();
		try {	
			OptionUtils.reload();
			Thread.sleep(20000);
		} catch (Exception e) {
			;
		}
	}
}*/

