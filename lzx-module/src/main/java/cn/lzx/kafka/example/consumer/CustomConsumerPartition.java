package cn.lzx.kafka.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指定消费者分区消费
 *
 * @author lzx
 * @since 2022-05-12
 */
public class CustomConsumerPartition {
    /**
     * log
     */
    private static final Logger logger = LoggerFactory.getLogger(CustomConsumerPartition.class);

    public static void main(String[] args) {
        /**
         * 1 配置
         */
        Map props = new HashMap<String, String>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "bootstrap");
        // 反序列化
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // Group id
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        /**
         * 2 创建消费者
         */
        KafkaConsumer kafkaConsumer = new KafkaConsumer<String, String>(props);
        /**
         * 3 订阅主题
         */
        List topics = new ArrayList<String>();
        topics.add("topic");
        kafkaConsumer.subscribe(topics);
        /**
         * 4 消费数据，拉取
         */
        while (true) {
            ConsumerRecords consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            logger.info("consumer:{}", consumerRecords);
        }
    }
}
