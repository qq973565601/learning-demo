package cn.lzx.video_upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lzx
 */
@Controller
@RequestMapping(value = "/public")
public class CustomPageController {
    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public Object toIndex() {
        return "index";
    }
}
