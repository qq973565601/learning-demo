package cn.lzx.video_upload.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Utils：JSON格式化
 * @author lzx
 */
public class JsonUtils {
    /**
     * 获取JSON字符串
     *
     * @param object 对象
     * @return JSON字符串
     */
    public static String getJson(Object object) {
        return getJson(object, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取JSON字符串
     *
     * @param object     对象
     * @param dateFormat 返回日期格式
     * @return JSON字符串
     */
    public static String getJson(Object object, String dateFormat) {
        // 创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        // 不使用时间差的方式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 自定义日期格式对象
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        // 指定日期格式
        mapper.setDateFormat(sdf);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取JSON字符串
     *
     * @param list       数据列表
     * @param dateFormat 返回日期格式
     * @return JSON字符串
     */
    public static String getJsonForList(List<Object> list, String dateFormat) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (Object obj : list) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(",");
                }
                stringBuffer.append(getJson(obj, dateFormat));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return "";
        }
    }
}