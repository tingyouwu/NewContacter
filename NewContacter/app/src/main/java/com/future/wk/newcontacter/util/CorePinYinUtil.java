package com.future.wk.newcontacter.util;


/**
 * 过滤汉字取得首字母
 */
public class CorePinYinUtil {

	public static String getPinyinFirstAlpha(String pinyin){
        
        if(pinyin == null)return "#";
        if(pinyin.equals(""))return "#";
        int ascii = (int)pinyin.toCharArray()[0];
        if( ascii < 65   || ascii>90  &&   ascii<97 || ascii>122){
            return "#";
        }else{
            return pinyin.toCharArray()[0]+"";
        }
    }
	
}
