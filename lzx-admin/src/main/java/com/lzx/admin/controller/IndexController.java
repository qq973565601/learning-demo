package com.lzx.admin.controller;

import com.lzx.admin.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * 登录跳转
 *
 * @author lzx
 * @since 2022/10/13
 */
@Slf4j
@Controller
public class IndexController {

    /**
     * 登录页
     *
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        log.info("执行登录");
        return "login";
    }

    /**
     * 表单提交，跳转主页
     *
     * @return
     */
    @PostMapping("/login")
    public String mainPage(User user, HttpSession session, Model model) {
        log.info("当前方法是：{}", "mainPage");
        if (!StringUtils.isEmpty(user.getUserName()) && !StringUtils.isEmpty(user.getPassword())) {
            session.setAttribute("loginUser", user);
            // 重重定向，防止表单重复提交
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "登录账号或密码错误");
            // 返回登录页
            return "login";
        }
    }

    /**
     * @return
     */
    @GetMapping("/main.html")
    public String goMainPage(HttpSession session, Model model) {
        log.info("当前方法是:{}","goMainPage");
        // 判断是否登录 拦截器、过滤器
        //Object attribute = session.getAttribute("loginUser");
        //if (!StringUtils.isEmpty(attribute)) {
        //    return "main";
        //} else {
        //    model.addAttribute("msg", "请重新登录");
        //    return "login";
        //}
        return "main";
    }
}
