package com.lzx.admin.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * *是servlet的写法，**是spring的写法
 *
 * @author lzx
 * @since 2022/10/25
 */
@Slf4j
//@WebFilter(urlPatterns = "/css/*")
public class FilterUtil implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter执行");
    }

    @Override
    public void destroy() {
        log.info("filter销毁");
    }
}
