package com.lzx.ppm.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utils：生成随机字符串
 */
public class RandomStrUtils {
    /**
     * 随机对象
     */
    private static Random random;
    /**
     * 字符库
     */
    private static String CODE_LIST_NUMBER = "1234567890";
    private static String CODE_LIST_varTER = "abcdefghigklmnopqrstuvwxyz";
    private static String CODE_LIST_ALL = "1234567890abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";

    /**
     * 判断字符串是否为数字
     * @param str 要验证的字符串
     * @return true 是数字串，false 不是数字串
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 生成订单号
     * @param headStr 订单起始标识字符串
     * @param endStrLength 订单号尾部随机字符串长度(如果长度小于等于0，以1为标准生效；如果长度大于等于20，以20为标准生效)
     * @return 订单号
     */
    public static String createOrderNumber(String headStr, int endStrLength) {
        String orderNumber = "";
        // 字符串头
        orderNumber = headStr == null ? "" : headStr.replaceAll(" ", "");
        // 如果长度小于等于0，以1为标准生效
        endStrLength = endStrLength <= 0 ? 1 : endStrLength;
        // 如果长度大于等于20，以20为标准生效
        endStrLength = endStrLength > 20 ? 20 : endStrLength;
        orderNumber = orderNumber + FormatDateUtils.getDateStr(new Date(), "yyyyMMddHHmmss") + createUserPwd(endStrLength);
        return orderNumber;
    }

    /**
     * 生成商品编号
     * @param start 字符头
     * @return 商品编号
     */
    public static String createProductNumber(String start) {
        String productNumber = "";
        Calendar date = Calendar.getInstance();
        productNumber = start == null ? "" : start + date.get(Calendar.YEAR)
                + (date.get(Calendar.MONTH) + 1)
                + date.get(Calendar.DAY_OF_MONTH)
                + date.get(Calendar.HOUR_OF_DAY) + date.get(Calendar.MINUTE)
                + date.get(Calendar.SECOND) + createRandomStr(3);
        return productNumber;
    }

    /**
     * 生成活动码
     * @return 活动码
     */
    public static String createActiveCode() {
        String activeCode = createRandomStr(3);
        Calendar date = Calendar.getInstance();
        activeCode = activeCode + date.get(Calendar.DAY_OF_MONTH)
                + date.get(Calendar.HOUR_OF_DAY) + date.get(Calendar.MINUTE)
                + date.get(Calendar.SECOND);
        return activeCode;
    }

    /**
     * 生成随机字符串
     * @param count 符串长度
     * @return 随机字符串
     */
    public static String createRandomStr(int count) {
        // 创建随机对象
        Random random = new Random();
        // 随机字符串
        String randomString = "";
        for (int i = 0; i < count; i++) {
            int rand = random.nextInt(CODE_LIST_varTER.length());
            String str = CODE_LIST_varTER.substring(rand, rand + 1);
            randomString += str;
        }
        return randomString;
    }

    /**
     * 生成随机密码
     * @param count 密码长度
     * @return 成随机密码
     */
    public static String createUserPwd(int count) {
        // 创建随机对象
        Random random = new Random();
        // 随机密码
        String pwdStr = "";
        for (int i = 0; i < count; i++) {
            int rand = random.nextInt(CODE_LIST_ALL.length());
            String randStr = CODE_LIST_ALL.substring(rand, rand + 1);
            pwdStr += randStr;
        }
        return pwdStr;
    }

    /**
     * 生成随机的数字字符串
     * @param count 长度
     * @return 随机数字字符串
     */
    public static String getNumberStr(int count) {
        // 创建随机对象
        Random random = new Random();
        // 随机密码
        String pwdStr = "";
        for (int i = 0; i < count; i++) {
            int rand = random.nextInt(CODE_LIST_NUMBER.length());
            String randStr = CODE_LIST_NUMBER.substring(rand, rand + 1);
            pwdStr += randStr;
        }
        return pwdStr;
    }

    /**
     * 生成随机数
     * @param size 最大值边界
     * @return 随机数
     */
    public static int createNumber(int size) {
        random = new Random();
        return (random.nextInt(size) + 1);
    }
}