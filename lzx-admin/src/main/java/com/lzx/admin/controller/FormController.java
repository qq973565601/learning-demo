package com.lzx.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author lzx
 * @since 2022/10/22
 */
@Slf4j
@Controller
public class FormController {

    /**
     * 文件上传页面跳转
     *
     * @return
     */
    @GetMapping("/form_layouts")
    public String formLayouts() {
        return "form/form_layouts";
    }

    /**
     * 文件上传
     *
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {
        log.info("上传的信息：email={}，username={}，headerImg={}，photos={}",
                email, username, headerImg.getSize(), photos.length);
        if (!headerImg.isEmpty()) {
            String originalFilename = UUID.randomUUID() + "_" + headerImg.getOriginalFilename();
            headerImg.transferTo(new File("C:\\Users\\lzx\\Pictures\\test\\" + originalFilename));
        }
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                String originalFilename = photo.getOriginalFilename();
                photo.transferTo(new File("C:\\Users\\lzx\\Pictures\\test\\"+UUID.randomUUID()+"_"+originalFilename));
            }
        }
        log.info("上传文件成功");
        return "main";
    }
}
