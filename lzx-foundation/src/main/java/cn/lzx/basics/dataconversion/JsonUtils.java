package cn.lzx.basics.dataconversion;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author lzx
 * @since 2023/8/13
 */
public class JsonUtils {

    public static Object jsonToJava(JSONObject jsonObject, Class o) {
        return JSONObject.toJavaObject(jsonObject, o);
    }

    public static Map<String, Object> jsonToMap(JSONObject jsonObject) {
        //Map<String, Object> map = jsonObject.getInnerMap();
        return null;
    }

}
