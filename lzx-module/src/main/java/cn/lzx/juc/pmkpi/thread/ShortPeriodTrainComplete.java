package cn.lzx.juc.pmkpi.thread;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lzx
 * @since 2022/9/26
 */
public class ShortPeriodTrainComplete {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortPeriodTrainComplete.class);

    private ExecutorService trainExecutor;
    @Value("${pm.shortPeriod.train.initThread:8}")
    private Integer trainInitThreadNum;
    @Value("${pm.shortPeriod.train.maxThread:8}")
    private Integer trainMaxThreadNum;
    @Value("${pm.shortPeriod.train.queueSize:500}")
    private Integer trainQueueSize;
    @Value("${pm.shortPeriod.train.keepAlive:60}")
    private Integer trainKeepAlive;
    @Value("${pm.shortPeriod.train.maxWait:60}")
    private Integer trainMaxWait;

    private ConcurrentHashMap<Future<JSONObject>, JSONObject> taskIdFutureMap =
            new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        trainExecutor = new ThreadPoolExecutor(
                trainInitThreadNum,
                trainMaxThreadNum,
                trainKeepAlive,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(trainQueueSize),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
    }

    public synchronized List<JSONObject> submitShortTrainYTask(List<String> message) {

        taskIdFutureMap.clear();
        LOGGER.info("short period train start");

        long startTime = System.currentTimeMillis();
        List<Future<JSONObject>> futures = new ArrayList<>();
        message.stream().forEach(payload -> {
            JSONObject payloadToJson = JSONObject.parseObject(payload);
            Future<JSONObject> trainFuture = trainExecutor.submit(new ShortPeriodTrainThread(payloadToJson));
            futures.add(trainFuture);
            taskIdFutureMap.put(trainFuture, payloadToJson);
        });
        List<JSONObject> trainResultList = new ArrayList<>();
        futures.stream().forEach(future -> {
            try {
                trainResultList.add(future.get(trainMaxWait, TimeUnit.SECONDS));
            } catch (Exception e) {
                LOGGER.error("short period train failed", e);
                JSONObject taskFailPayload = taskIdFutureMap.get(future);
                if (taskFailPayload != null && taskFailPayload.size() != 0) {
                    // todo 封装失败消息
                }
            }
        });
        LOGGER.info("train period result:{}", trainResultList.toString());
        LOGGER.info("handler train period time={},size={}", System.currentTimeMillis() - startTime, message.size()); 
        return trainResultList;
    }
}
