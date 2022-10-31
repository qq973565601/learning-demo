package com.lzx.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.lzx.admin")
@SpringBootApplication
public class LzxAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LzxAdminApplication.class, args);
    }

}
