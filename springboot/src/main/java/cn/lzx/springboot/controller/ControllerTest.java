package cn.lzx.springboot.controller;

import cn.lzx.common.utils.JsonResult;
import cn.lzx.springboot.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lzx
 */
@RestController
@RequestMapping("/test")
public class ControllerTest {

    @RequestMapping("/getUser")
    public JsonResult getUser(){
        User user = new User(1L,"lzx","lzx12345");
        return JsonResult.ok("获取用户成功");
    }
}
