package cn.lzx.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装统一返回的数据结构
 *
 * @author lzx
 * @since 2022-06-10
 */
public class JsonResult<T> extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 若没有数据返回，默认状态码为 0，提示信息为：操作成功！
     */
    public JsonResult() {
        put("code", "0");
        put("msg", "success");
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     *
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    /**
     * 有数据返回时，状态码为 0，默认提示信息为：操作成功！
     *
     * @param data
     */
    public JsonResult(T data) {
        put("data", data);
        put("code", "code");
        put("msg", "success");
    }

    /**
     * 有数据返回，状态码为 0，人为指定提示信息
     *
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        put("data", data);
        put("code", "0");
        put("msg", msg);
    }

    /**
     * 利用FastJson进行逆转
     *
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T getData(TypeReference<T> typeReference) {
        Object data = get("data");
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }

    /**
     * @return
     */
    public <T> T getData(String key, TypeReference<T> tTypeReference) {
        Object data = this.get(key);
        String toJSONString = JSON.toJSONString(data);
        T t = JSON.parseObject(toJSONString, tTypeReference);
        return t;
    }

    /**
     * 设置数据
     *
     * @param data
     * @return
     */
    public JsonResult<T> setData(Object data) {
        put("data", data);
        return this;
    }

    /**
     * 获取状态码
     *
     * @return
     */
    public Integer getCode() {
        return (Integer) this.get("code");
    }

    /**
     * 返回响应
     *
     * @param msg
     * @return
     */
    public static JsonResult ok(String msg) {
        JsonResult r = new JsonResult();
        r.put("msg", msg);
        return r;
    }

    /**
     * 返回响应
     *
     * @param map
     * @return
     */
    public static JsonResult ok(Map<String, Object> map) {
        JsonResult r = new JsonResult();
        r.putAll(map);
        return r;
    }

    /**
     * 返回响应
     *
     * @return
     */
    public static JsonResult ok() {
        return new JsonResult();
    }

    /**
     * 设置数据
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public JsonResult<T> put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 异常状态处理
     *
     * @return
     */
    public static JsonResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static JsonResult error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static JsonResult error(int code, String msg) {
        JsonResult r = new JsonResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
}
