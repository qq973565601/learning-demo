package cn.lzx.ssm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzx
 */
public class Msg {

    /**
     * 状态码 100-成功 200-失败
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 用户要返回给浏览器的数据
     */
    private Map<String, Object> extend = new HashMap<String, Object>();

    /**
     * 处理成功方法
     *
     * @return
     */
    public static Msg success() {
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMsg("处理成功");
        return msg;
    }

    /**
     * 处理失败方法
     *
     * @param
     */
    public static Msg fail() {
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("处理失败");
        return msg;
    }

    /**
     * 链式操作，添加数据
     *
     * @param code
     */
    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }
}
