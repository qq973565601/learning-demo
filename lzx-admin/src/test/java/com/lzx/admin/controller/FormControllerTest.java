package com.lzx.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 文件上传测试
 *
 * @author lzx
 * @since 2022/10/22
 */
@Controller
public class FormControllerTest {

    @GetMapping("/form_layouts")
    public String formLayouts() {
        return "form/form_layouts";
    }
}
