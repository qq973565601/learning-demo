package cn.lzx.controller;

import cn.lzx.sqlserver.SqlTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试请求域注解
 * 页面跳转
 *
 * @author lzx
 * @since 2022/10/12
 */
@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功了");
        request.setAttribute("code", 200);
        return "forward:success";
    }

    /**
     * 模拟接受请求
     *
     * @return data
     */
    @ResponseBody
    @GetMapping("/success")
    public Map<String, Object> success(@RequestAttribute("msg") String msg,
                                       @RequestAttribute("code") Integer code,
                                       HttpServletRequest request) {
        Object attributeMsg = request.getAttribute("msg");
        Object attributeCode = request.getAttribute("code");
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        map.put("attributeMsg", attributeMsg);
        map.put("attributeCode", attributeCode);
        return map;
    }

    @ResponseBody
    @GetMapping("/json")
    public String getJson() {
        SqlTest sqlTest = new SqlTest();
        String json = sqlTest.getJson();
        return "hello";
    }
}
