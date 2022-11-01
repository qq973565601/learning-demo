package com.lzx.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class LzxAdminApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
        log.info("数据源类型：{}", dataSource.getClass());
    }

}
