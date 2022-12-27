package com.lzx.ppm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utils：字符串工具类
 */
public class StringUtils {
    /***
     * 验证字符串是否为空
     * @param obj 待验证的字符串
     * @return true为空，false不为空
     */
    public static boolean isEmpty(Object obj){
        return (null==obj||obj.equals("")||obj.toString().replaceAll(" ","").equals(""))?true:false;
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     * @param str 待处理字符串
     * @return 字符串
     */
    public static String replaceBlank(String str){
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
