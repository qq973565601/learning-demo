package com.lzx.admin.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 项目启动监听器
 *
 * @author lzx
 * @since 2022/10/25
 */
@Slf4j
@WebListener
public class ServletContextListenerUtil implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("监听项目初始化完成");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("监听项目销毁完成");
    }
}
