package com.lzx.ppm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Utils：日期格式化
 */
public class FormatDateUtils {
    /**
     * 字符串转日期
     *
     * @param date   日期字符串
     * @param format 格式化字符串
     * @return 日期对象
     */
    public static Date getDate(String date, String format) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        Date formatdate = null;
        try {
            formatdate = df.parse(date);
        } catch (ParseException e) {
            // e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return formatdate;
    }

    /**
     * 日期转字符串
     *
     * @param date   日期对象
     * @param format 格式化字符串
     * @return 日期字符串
     */
    public static String getDateStr(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        String fromatDateStr = df.format(date);
        return fromatDateStr;
    }

    /**
     * 分解字符串
     *
     * @param str 要分解的字符串
     * @return 字符串组
     */
    public static ArrayList<String> getStringList(String str) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i) + "");
        }
        return list;
    }
}