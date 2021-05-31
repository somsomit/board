package com.spring.board.common.util;

public class Util {
	
	public static int nvl(String text){
		return nvl(text, 0);
	}
	
	/*
	 	nvl : 문자열을 숫자로 변환
	 	@param (숫자로 변환할 문자열, 초기값으로 사용할 값(대체값))
	 	@return int
	 */
	
	public static int nvl(String text, int def){
		int ret = def;
		try{
			ret = Integer.parseInt(text);
		}catch(Exception e){
			ret = def;
		}
		return ret;
	}
	
	public static String nvl(Object text, String def){
		if(text==null || "".equals(text.toString().trim())){
			return def;
		}else{
			return text.toString();
		}
	}

}
