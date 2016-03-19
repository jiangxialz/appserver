package com.maker.app.constant;

/**
 * 币种枚举
 * @author hlz
 * @date 2015-7-6 下午6:14:43
 */
public enum CurrencyEnum {
	
	/*通过括号赋值,而且必须有带参构造器和一属性跟方法，否则编译出错
     * 赋值必须是都赋值或都不赋值，不能一部分赋值一部分不赋值
     * 如果不赋值则不能写构造器，赋值编译也出错*/
    CNY("CNY"), USD("USD");
    
    private final String value;
    public String getValue() {
        return value;
    }
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    CurrencyEnum(String value) {
        this.value = value;
    }
    
    public static void main(String[] args) {
    	 for(CurrencyEnum t : CurrencyEnum.values()){
             /*通过getValue()取得相关枚举的值*/
             System.out.println(t+"的值是"+t.getValue());
         }
	}

}
