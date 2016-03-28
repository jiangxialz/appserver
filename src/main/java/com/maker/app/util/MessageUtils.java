package com.maker.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 多语言工具类
 * 
 * @author amin
 * @desc 只扫描i18n文件下的一级目录文件命名格式：message_语言key.properties(文件类型目前只支持properties格式)，语言key为浏览器对应的键值.ps:message_zh-CN.properties为简体中文
 */
public class MessageUtils {

	protected static Log logger = LogFactory.getLog(MessageUtils.class);
	
	/**
	 * 文件名前缀
	 */
	private static final String prefix = "message";

	/**
	 * 多语言文件信息
	 */
	public static final Map<String, MessageFileInfo> mesMaps = new HashMap<String, MessageFileInfo>();

	static {//调用的时候初始化
		init();
	}
	
	/**
	 * 初始化
	 */
	public static void init() {
		try {
			String mesPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("%20", " ") + "/i18n";
			// 加载文件内容
			File file = new File(mesPath);
			// 路径只能为目录才加载,且只支持一级目录
			if (file.isDirectory()) {
				String[] filelist = file.list();

				String fileName = null;// 文件名
				String ln = null;// 语言
				Properties lnfile = null;// 多语言文件
				int startIndex = 0;// 开始索引
				// 遍历文件夹下所有文件
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(mesPath + "/" + filelist[i]);// 读取的文件
					if (!readfile.isDirectory()) {
						try{
							fileName = readfile.getName();
							startIndex = fileName.indexOf("_"); 
							
							lnfile = new Properties();
							lnfile.load(new FileInputStream(readfile));
							
							//截取文件名对应的语言
							ln = fileName.substring(startIndex + 1, fileName.lastIndexOf("."));
							//添加到集合
							mesMaps.put(ln, new MessageFileInfo(lnfile, readfile.lastModified()));
						}catch(Exception e){
							logger.error("MessageUtils read file error:" + e.getMessage() + ",fileName=" + fileName, e);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("MessageUtils init error:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 重新加载
	 */
	public static void reload(){
		try{
			if(mesMaps != null && mesMaps.size() > 0){
				String mesPath = MessageUtils.class.getResource("/i18n").getPath();
				
				File file = null;
				MessageFileInfo fileInfo = null;
				Properties lnfile = null;// 多语言文件
				for (Map.Entry<String, MessageFileInfo> entry : mesMaps.entrySet()) {
					file = new File(mesPath + prefix + "_" + entry.getKey() + ".properties");
					fileInfo = entry.getValue();
					if(fileInfo.getLastmodifytime() != file.lastModified()){// 修改时间不为
						
						// 重新加载
						lnfile = new Properties();
						lnfile.load(new FileInputStream(file));
						
						fileInfo = new MessageFileInfo(lnfile, file.lastModified());
					}
				}
			}
		}catch(Exception e){
			logger.error("MessageUtils reload error:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 返回格式化多语言信息
	 */
	public static String getText(HttpServletRequest request, String code, Object... arguments){
		String value = getText(request, code);
		return MessageFormat.format(value, arguments);
	}
	
	/**
	 * 根据配置文件语言获取多语言信息
	 * @param locale 多语言（zh-CN,zh-TW,en等）
	 * @param code 多语言键
	 * @return 对应多语言信息
	 */
	public static String getText(String code){
		return getProperty(getLocale(OptionUtils.getStringOption("sysconfig.locale")), code);
	}
	
	/**
	 * 根据请求语言获取多语言信息
	 * @param locale 多语言（zh-CN,zh-TW,en等）
	 * @param code 多语言键
	 * @return 对应多语言信息
	 */
	public static String getText(String locale, String code){
		return getProperty(getLocale(locale), code);
	}
	
	/**
	 * 返回格式化多语言信息
	 */
	public static String getText(String locale, String code, Object... arguments){
		String value = getText(locale, code);
		return MessageFormat.format(value, arguments);
	}
	
	
	/**
	 * 如果不存在该多语言则返回默认语言
	 * 优先顺序: 1.传入的语言 -- > 2.系统配置的语言 -- > 3.默认语言 -- > 4. null
	 */
	private static Properties getLocale(String locale){
		if(mesMaps.containsKey(locale)){
			return mesMaps.get(locale).getProperties();
		}
		String sys = OptionUtils.getStringOption("sysconfig.locale");// 读取配置的语言
		if(mesMaps.containsKey(sys)){
			return mesMaps.get(sys).getProperties();
		}
		return null;
	}
	
	/**
	 * 返回配置多语言信息
	 * @param p 多语言配置文件
	 * @param code 多语言key
	 */
	private static String getProperty(Properties p, String code){
		if(p == null){
			return "Unknown Error!";
		}
		return p.getProperty(code);
	}
	
	public static void main(String[] args) {
		init();
	}

}
