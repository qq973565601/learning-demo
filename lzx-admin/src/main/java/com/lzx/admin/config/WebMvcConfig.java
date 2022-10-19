package com.lzx.admin.config;

import com.lzx.admin.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定制springMVC的功能
 *
 * 拦截器使用步骤
 * 1.编写一个拦截器实现HandlerInterceptor接口
 * 2.拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
 * 3.指定拦截规则【如果是拦截所有，静态资源也会被拦截】
 *
 * @author lzx
 * @since 2022/10/19
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 登录请求拦截
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("执行拦截");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//拦截请求，包括静态资源
                .excludePathPatterns("/", "/login","/css/**","/fonts/**","/images/**","/js/**");//放行请求
    }
}
