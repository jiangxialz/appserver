package com.maker.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 参数配置属性文件
 * @author lz
 * 2014-11-25 下午2:10:24
 *
 */
public class OptionUtils {
	
    private static Logger logger = Logger.getLogger(OptionUtils.class);
    private static long optionFileLastModifyTime = 0L;

    public static Properties options = new Properties();

    static{
        try{
            String confFileUrl = OptionUtils.class.getResource("/conf/options.properties").getPath();
            File file = new File(confFileUrl);
            optionFileLastModifyTime = file.lastModified();
            InputStream is = new FileInputStream(file);
            options.load(is);
            logger.info("load options.properties success...");
        }catch(Exception e){
            logger.error("can not get file options.properties " + e.getMessage());
        }
    }
    
    
    public static void reload(){
        try{
            String confFileUrl = OptionUtils.class.getResource("/conf/options.properties").getPath();
            File file = new File(confFileUrl);
            if(optionFileLastModifyTime != file.lastModified()){
                logger.info("reload options.properties, file.lastModified() = " + file.lastModified());
                options.clear();
                InputStream is = new FileInputStream(file);
                options.load(is);
                optionFileLastModifyTime = file.lastModified();
            }
        }catch(Exception e){
            logger.error(" can not get file options.properties " + e.getMessage());
        }
    }

    /**
     * 获取options文件key值
     * 
     * @param key
     * @return int
     */
    public static int getIntOption(String key) {
        return getIntOption(key, 0);
    }

    public static int getIntOption(String key, int defaultValue) {
        int result = defaultValue;
        String value = options.getProperty(key);
        if (StringUtils.isNotBlank(value)) {
            try {
                result = Integer.parseInt(value.trim());
            } catch (Exception e) {
                logger.error("error fomart String to Integer:" + e);
            }
        }
        return result;
    }

    /****
     * 获取options文件key值
     * 
     * @param key
     * @return
     */
    public static String getStringOption(String key) {
        return getStringOption(key, "");
    }

    /**
     * 获取options文件key值，如果为空返回默认值 defaultValue
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getStringOption(String key, String defaultValue) {
        String value = options.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value.trim();
    }

    public static long getLongOption(String key) {
        return getLongOption(key, 0L);
    }

    public static long getLongOption(String key, long defaultValue) {
        long result = defaultValue;
        String value = options.getProperty(key);
        if (StringUtils.isNotBlank(value)) {
            try {
                result = Long.parseLong(value.trim());
            } catch (Exception e) {
                logger.error("error fomart String to Long:" + e);
            }
        }
        return result;
    }

    public static double getDoubleOption(String key) {
        return getDoubleOption(key, 0.0D);
    }

    public static double getDoubleOption(String key, double defaultValue) {
        double result = defaultValue;
        String value = options.getProperty(key);
        if (StringUtils.isNotBlank(value)) {
            try {
                result = Double.parseDouble(value.trim());
            } catch (Exception e) {
                logger.error("error fomart String to Double:" + e);
            }
        }
        return result;
    }

    public static boolean getBooleanOption(String key) {
        return getBooleanOption(key, false);
    }

    public static boolean getBooleanOption(String key, boolean defaultValue) {
        boolean result = defaultValue;
        String value = options.getProperty(key);
        if (StringUtils.isNotBlank(value)) {
            try {
                result = Boolean.parseBoolean(value.trim());
            } catch (Exception e) {
                logger.error("error fomart String to Boolean:" + e);
            }
        }
        return result;
    }

    /**
     * 替换{0}等类型
     * 
     * @param message
     * @param key
     * @param replacement
     * @return
     */
    public static String replaceValue(String value, String key, String replacement) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        if (StringUtils.isEmpty(key)) {
            return value;
        }
        if (StringUtils.isEmpty(replacement)) {
            return value;
        }
        return value.replaceAll(key, replacement);
    }

    /****
     * 替换{0}{1}等类型
     * 
     * @param message
     * @param replacement
     * @return
     */
    public static String replaceValue(String value, List<String> replacement) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        if (replacement == null) {
            return value;
        }
        Object[] rro = replacement.toArray();
        return String.format(value, rro);
    }

}