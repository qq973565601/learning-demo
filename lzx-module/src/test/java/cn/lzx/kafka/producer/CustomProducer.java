package cn.lzx.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 异步发送
 *
 * @author lzx
 * @since 2022.5.6
 */
public class CustomProducer {
    public static void main(String[] args) {
        /**
         * 1 配置
         */
        Properties properties = new Properties();
        // 集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop:port");
        // 指定序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 关联自定义分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "cn.lzx.kafka.producer.MyPartitioner");
        /**
         * 2 创建Kafka生产者对象
         */
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        /**
         * 3 发送数据
         */
        for (int i = 0; i < 5; i++) {
            /**
             * 同步：producer.send(new ProducerRecord<>("first", "hello")).get();
             */
            producer.send(new ProducerRecord<>("first", "hello"));

        }
        /**
         * 4 关闭资源
         */
        producer.close();
    }
}
