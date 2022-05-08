package cn.lzx.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义
 *
 * @author lzx
 */
public class MyPartitioner {

    private static final Logger logger = LoggerFactory.getLogger(MyPartitioner.class);

    public static void main(String[] args) {
        logger.info("hello");
        System.out.println("s+hello");
    }
}
