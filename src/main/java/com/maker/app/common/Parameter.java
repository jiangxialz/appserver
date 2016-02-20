package com.maker.app.common;

/**
 * 参数对象
 * @author hlz
 * @date 2015-5-5
 */
public class Parameter {
	private ParameterTypes type;
	private String name;
	private boolean required;
	private int maxLength;
	private String value;
	
	/**
	 * 校验参数是否合法，如果合法，返回OK
	 * 否则返回参数错误的原因
	 * @return 校验结果
	 */
	public String validate() {
		
		if (required) { //如果是必填项，又没有值，返回错误
			if (value == null || value.trim().length() == 0)
				return "缺少必须的参数："+name;
		} else if (value == null || value.trim().length() == 0) { //如果非必填项，又没有值，校验通过
			return "OK";
		}
		
		switch (type) {
		case INT:
			return (value.matches("\\d{1,10}")) ? "OK" : "参数"+name+" 不是正确的int类型";
		case LONG:
			return (value.matches("\\d{1,19}")) ? "OK" : "参数"+name+" 不是正确的long类型";
		case FLOAT:
			return (value.matches("[\\d\\.]{1,7}")) ? "OK" : "参数"+name+" 不是正确的float类型";
		case DOUBLE:
			return (value.matches("[\\d\\.]{1,16}")) ? "OK" : "参数"+name+" 不是正确的double类型";
		case DATE:
			return (value.matches("((19)|(20))\\d{2}\\-[0-1]\\d\\-[0-3]\\d")) ? 
					"OK" : "参数"+name+" 不是正确的Date(yyyy-mm-dd)类型";
		case DATETIME:
			return (value.matches("((19)|(20))\\d{2}\\-[0-1]\\d\\-[0-3]\\d [0-2]\\d:[0-5]\\d:[0-5]\\d")) ? 
					"OK" : "参数"+name+" 不是正确的Datetime(yyyy-mm-dd hh:mm:ss)类型";
		case EMAIL:
			return (value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) ? 
					"OK" : "参数"+name+" 不是正确的email(someone@domain.com)类型";
		case MOBILE:
			return (value.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")) ? 
					"OK" : "参数"+name+" 不是正确的手机号码格式(13800138000)";
		case TELEPHONE:
			return (value.matches("\\d{3,4}\\-\\d{7,8}(\\-\\d{1,8})?")) ? 
					"OK" : "参数"+name+" 不是正确的电话号格式(0755-88880000-230)";
		}
		
		if (maxLength > 0 && value.length() > maxLength)
			return "参数"+name+"长度超过，规定最大长度："+maxLength+"，而实际长度为："+value.length();
		
		return "OK";
	}
	
	/**
	 * 克隆现有的参数对象
	 */
	public Parameter clone() {
		Parameter param = new Parameter();
		param.setName(name);
		param.setMaxLength(maxLength);
		param.setRequired(required);
		param.setType(type);
		param.setValue(value);
		return param;
	}
	
	/**
	 * 解析参数值，把String类型的参数值转换成实际需要的类型
	 * @return 转换后的参数值
	 */
	public Object parseValue() {
		if (value == null || value.trim().length() == 0)
			return value;
		switch (type) {
		case INT:
			return Integer.parseInt(value);
		case LONG:
			return Long.parseLong(value);
		case FLOAT:
			return Float.parseFloat(value);
		case DOUBLE:
			return Double.parseDouble(value);
		case DATE:
			return java.sql.Date.valueOf(value);
		case DATETIME:
			return java.sql.Timestamp.valueOf(value);
		}
		return value;
	}
	
	public static void main(String[] args) {
		System.out.println("abc\\".replaceAll("\\\\", "\\\\\\\\"));
		System.out.println("".matches("\\d{1,19}"));
		System.out.println("0755-82820011-233".matches("\\d{3,4}\\-\\d{7,8}(\\-\\d{1,8})?"));
		System.out.println("13800138000".matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"));
		System.out.println("1913-09-10".matches("((19)|(20))\\d{2}\\-[0-1]\\d{1}\\-[0-3]\\d{1}"));
		System.out.println("2013-09-10 07:48:21"
				.matches("((19)|(20))\\d{2}\\-[0-1]\\d\\-[0-3]\\d [0-2]\\d:[0-5]\\d:[0-5]\\d"));
		System.out.println("linyf.018@qq.com.cn".matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"));
	}
	
	public ParameterTypes getType() {
		return type;
	}
	public void setType(ParameterTypes type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
}
