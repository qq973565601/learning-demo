package cn.lzx.kafka.example.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 自定义分区器
 *
 * @author lzx
 */
public class MyPartitioner implements Partitioner {

    private static final Logger logger = LoggerFactory.getLogger(MyPartitioner.class);

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String msgValue = value.toString();
        int partition;
        if (msgValue.contains("msg")) {
            return partition = 1;
        } else {
            return 0;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
