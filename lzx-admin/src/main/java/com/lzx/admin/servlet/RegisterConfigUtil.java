package com.lzx.admin.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 保证依赖的组件为单实例，减少对象创建
 * @author lzx
 * @since 2022/10/25
 */
@Configuration(proxyBeanMethods = true)
public class RegisterConfigUtil {

    @Bean
    public ServletRegistrationBean servletTest() {
        ServletUtil servletUtil = new ServletUtil();
        return new ServletRegistrationBean(servletUtil, "/my");
    }

    @Bean
    public FilterRegistrationBean filterTest() {
        FilterUtil filterUtil = new FilterUtil();
        //return new FilterRegistrationBean(filterUtil, servletTest());
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filterUtil);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my", "/two"));
        return filterRegistrationBean;
    }
}
