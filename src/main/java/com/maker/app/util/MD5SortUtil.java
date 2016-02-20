
package com.maker.app.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 将map中的参数按规则进行字典排序并加密
 * @author hlz
 * @date 2015-6-12 下午4:46:58
 */
public class MD5SortUtil {
	
	public static final String CHARACTER_ENCODING = "UTF-8";
    
    /**
	 * 将map中的参数按规则进行字典排序并加密
	 * @param params 需要排序的参数
	 * @param removeSpecificParam 去除的特定参数
	 * @param removeEmptyValue 是否移除为空参数值
	 * @param splitChar 键值对连接字段，默认为&
	 * @return
	 */
	public static String signKeyAndValue(Map<String, String> params, String removeSpecificParam, 
			boolean removeEmptyValue, String splitChar) {
		
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
	    // 按照key做排序
		Collections.sort(keys);
		if(splitChar == null){ // 默认以“&”分割
			splitChar = "&";
		}

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			// 移除特定参数
			if (null != removeSpecificParam && removeSpecificParam.equals(key)) {
				continue;
			}
			String value = params.get(key);
			if (value == null || value.toString().length() == 0) {
				if(removeEmptyValue){
					continue;
				}else {
					content.append((i == 0 ? "" : splitChar) + key + "=");
				}
				
			} else {
				content.append((i == 0 ? "" : splitChar) + key + "=" + value);
			}
		}
		
		String signData = content.toString();
		System.out.println("signData : " + signData);
		
		return DigestUtils.md5Hex(getContentBytes(signData, CHARACTER_ENCODING));
	}
	
   /**
	 * 将map中的参数按规则进行字典排序并加密
	 * @param params 需要排序的参数
	 * @param removeSpecificParam 去除特定参数
	 * @param removeEmptyValue 是否移除为空参数值
	 * @param splitChar 参数值连接字段，默认为&
	 * @return
	 */
	public static String signValue(Map<String, String> params, String removeSpecificParam, 
			boolean removeEmptyValue, String splitChar) {
		
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
	    // 按照key做排序
		Collections.sort(keys);
		if(StringUtils.isBlank(splitChar)){ // 默认以“&”分割
			splitChar = "&";
		}

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			// 移除特定参数
			if (null != removeSpecificParam && removeSpecificParam.equals(key)) {
				continue;
			}
			String value = params.get(key);
			if (value == null || value.toString().length() == 0) {
				if(removeEmptyValue){
					continue;
				}else {
					content.append((i == 0 ? "" : splitChar) + value);
				}
			} else {
				content.append((i == 0 ? "" : splitChar) + value);
			}
		}
		String signData = content.toString();
		System.out.println("signData : " + signData);
		
		return DigestUtils.md5Hex(getContentBytes(signData, CHARACTER_ENCODING));
	}
	
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名时编码错误，请检查编码！");
        }
    }
    
	// do main
    public static void main(String[] args) {
		try{
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("a", "");
			map.put("r1", "111111");
			map.put("r5", "555555");
			map.put("r2", "222222");
			map.put("s4", "444444");
			map.put("r250", "250250");
			
			System.out.println(signValue(map, null, true, ""));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
}