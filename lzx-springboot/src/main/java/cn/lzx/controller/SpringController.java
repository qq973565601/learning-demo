package cn.lzx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzx
 * @since 2022/10/10
 */
@RestController
public class SpringController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser() {
        return "GET-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveUser() {
        return "POST-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String putUser() {
        return "PUT-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String deleteUser() {
        return "DELETE-张三";
    }

}
