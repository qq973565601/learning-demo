package com.lzx.admin.test;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lzx
 * @since 2022/11/6
 */
@DisplayName("Junit5测试类")
@SpringBootTest
public class Junit5Test {

    @BeforeEach
    void testBeforeEach() {
        System.out.println("每个测试之前执行方法...");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("每个测试之后执行方法...");
    }

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试之前执行方法...");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试之后执行方法...");
    }

    @DisplayName("displayName测试方法")
    @Test
    void testDisplayName() {
        System.out.println("测试displayName");
    }

    @RepeatedTest(2)
    @Test
    void testRepeated() {
        System.out.println("重复测试");
    }

    @DisplayName("测试前置条件")
    @Test
    void testAssumptions() {
        Assumptions.assumeTrue(true, "结果不是true");
        // 前置条件正确的情况下
        System.out.println("前置条件测试...");
    }
}
