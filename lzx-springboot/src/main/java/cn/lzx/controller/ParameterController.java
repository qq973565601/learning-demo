package cn.lzx.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数传递方法测试
 *
 * @author lzx
 * @since 2022/10/11
 */
@RestController
public class ParameterController {

    /**
     * 通过路径多个变量传入参数
     * 路径样式：car/2/owner/zhangsan
     *
     * @param id        路径param
     * @param userName  路径param
     * @param pv        存贮多个信息，根据规定写泛型
     * @param userAgent 请求头信息
     * @param header    全零请求头信息
     * @param age       变量参数
     * @param inters    变量参数列表
     * @param param     变量参数集合
     * @return 响应
     */
    @GetMapping("/car/{id}/owner/{userName}")
    public Map<String, Object> getParam(@PathVariable("id") Integer id,
                                        @PathVariable("userName") String userName,
                                        @PathVariable Map<String, String> pv,
                                        @RequestHeader("User-Agent") String userAgent,
                                        @RequestHeader Map<String, String> header,
                                        @RequestParam("age") Integer age,
                                        @RequestParam("inters") List<String> inters,
                                        @RequestParam Map<String, String> param) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", userName);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("header", header);
        map.put("age", age);
        map.put("inters", inters);
        map.put("param", param);
        return map;
    }

    @GetMapping("/cookie")
    public Map<String, Object> getCookie(@CookieValue("_ga") String _ga,
                                         @CookieValue("_ga") Cookie cookie) {
        Map<String, Object> map = new HashMap<>();
        map.put("_ga", _ga);
        System.out.println(cookie);
        return map;
    }

    @PostMapping("/body")
    public Map<String, Object> getBody(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        System.out.println(content);
        JSONObject parseObject = JSONObject.parseObject(content);
        System.out.println("JSon:" + parseObject);
        return map;
    }
}
