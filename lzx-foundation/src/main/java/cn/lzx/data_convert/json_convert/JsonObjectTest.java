package cn.lzx.data_convert.json_convert;

import cn.lzx.data_convert.json.AutoGetJsonObjectJson;
import cn.lzx.data_convert.json.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Json数据转换
 *
 * @author lzx
 * @since 2023/8/13
 */
public class JsonObjectTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonObjectTest.class);

    /**
     * JSON转Class
     */
    @Test
    public void jsonToJava() {
        JSONObject jsonParam = AutoGetJsonObjectJson.jsonToObject();
        User user = JSONObject.toJavaObject(jsonParam, User.class);
        LOGGER.info("输出结果为：{}", user.toString());
    }

    /**
     * JSON转Map
     */
    @Test
    public void jsonToMap() {
        JSONObject jsonParam = AutoGetJsonObjectJson.jsonToObject();
        Map<String, Object> innerMap = jsonParam.getInnerMap();
        LOGGER.info("输出结果为：{}", innerMap.toString());
    }

    /**
     * JSON转String
     */
    @Test
    public void jsonToString() {
        JSONObject jsonParam = AutoGetJsonObjectJson.jsonToObject();
        //关闭空字符串过滤
        String s = JSON.toJSONString(jsonParam, SerializerFeature.WriteMapNullValue);
        LOGGER.info("输出结果为：{}", s);
    }

    /**
     * String转JSON
     */
    @Test
    public void stringToJson() {
        JSONObject jsonParam = AutoGetJsonObjectJson.jsonToObject();
        String s = JSON.toJSONString(jsonParam, SerializerFeature.WriteMapNullValue);
        JSONObject jsonObject = JSON.parseObject(s);
        LOGGER.info("输出结果为：{}", jsonObject);
    }

    /**
     * 驼峰转下划线
     */
    @Test
    public void humpToUnderline() {
        JSONObject jsonParam = AutoGetJsonObjectJson.jsonToObject();
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(jsonParam, config));
        LOGGER.info("输出结果为：{}", jsonObject);
    }

    /**
     * 格式化日期
     */
    @Test
    public void dataFormat() {
        JSONObject jsonParam = AutoGetJsonObjectJson.jsonToObject();
        //第一种
        String s = JSON.toJSONStringWithDateFormat(jsonParam, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        LOGGER.info("输出结果为：{}", s);
        // 第二种
        JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";//设置日期格式
        String s1 = JSONObject.toJSONString(jsonParam, SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
        LOGGER.info("输出结果为：{}", s1);
    }

}
