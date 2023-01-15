package cn.lzx.kafka.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 提高producer吞吐量，添加配置
 * @since 2022-05-10
 */
public class CustomProducerParameters {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1 配置信息
        Map props = new HashMap();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "bootstrap");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 批次大小，默认 16K
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 等待时间，默认 0
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 缓冲区大小，默认 32M：buffer.memory
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 压缩，默认 none，可配置值 gzip、snappy、lz4 和 zstd
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        // ack
        props.put(ProducerConfig.ACKS_CONFIG, 1);
        // 重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 2 创建生产者
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        // 3 发送数据
        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<>("topic", "msg")).get();
        }
        // 4 关闭资源
        kafkaProducer.close();
    }
}
