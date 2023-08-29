package cn.lzx.data_convert.json;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON转换测试数据
 *
 * @author lzx
 * @since 2023/8/29
 */
public class AutoGetJsonObjectJson {

    public static JSONObject jsonToObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "1");
        jsonObject.put("user_name", "林宗星");
        jsonObject.put("age", "28");
        JSONObject object = new JSONObject();
        object.put("city", "合肥");
        jsonObject.put("addr", object);
        jsonObject.put("create_time", "1900-01-01 00:00:00");
        return jsonObject;
    }
}
