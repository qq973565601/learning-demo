package cn.lzx.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 异步发送，回调函数信息
 *
 * @author lzx
 */
public class CustomProduceCallback {
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
        /**
         * 2 创建Kafka生产者对象
         */
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        /**
         * 3 发送数据
         */
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<>("first", "hello"), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null){
                        System.out.println("主题:" + recordMetadata.topic()+"，分区："+recordMetadata.partition());
                    }
                }
            });
        }
        /**
         * 4 关闭资源
         */
        producer.close();
    }
}
