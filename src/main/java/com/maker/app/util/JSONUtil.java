package com.maker.app.util;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * json处理类
 * @author hlz
 * @date 2015-5-5
 */
public class JSONUtil {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static {

	}

	
	/**
	 * 对象转为json字符串
	 * @param obj
	 * @return
	 */
	public static String toJSON(Object obj) {
		try {
		    //设置日期显示格式
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 对象转为json字符串
	 * @param obj
	 * @return
	 */
	public static String toJSON(Object obj, String dataFormat) {
		try {
			//设置日期显示格式
			mapper.setDateFormat(new SimpleDateFormat(dataFormat));
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <json字符串转为实体类>
	 * <功能详细描述>
	 * @param jsonStr
	 * @param valueType
	 * @return [参数说明]
	 * 
	 * @return T [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T fromJSON(String jsonStr, Class<T> valueType){
		try {
			//设置日期显示格式
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return mapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}