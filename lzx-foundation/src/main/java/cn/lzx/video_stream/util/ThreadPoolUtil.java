package cn.lzx.video_stream.util;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author lzx
 * @since 2023/1/17
 */
@Slf4j
public class ThreadPoolUtil {

    private static final int CORE_POOL_SIZE = (int) (Runtime.getRuntime().availableProcessors() / (1 - 0.5f));
    private static final int MAX_POOL_SIZE = 35;
    private static final long KEEP_LIVE_TIME = 60L;
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new LinkedBlockingQueue<>();
    public static final ThreadPoolExecutor POOL;

    static{
        POOL = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_LIVE_TIME,
                TimeUnit.MILLISECONDS,
                BLOCKING_QUEUE,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    /**
     * 取消当前正在执行的任务
     */
    public static void cancelTask(Future<?> future) {
        // 终止正在执行的任务
        if (ObjectUtil.isNotNull(future) && !future.isDone() && !future.isCancelled()) {
            future.cancel(true);
        }
    }

    /**
     * 释放线程池
     */
    public static void shutdownAndAwaitTermination() {
        if (POOL != null && !POOL.isShutdown()) {
            POOL.shutdown();
            try {
                if (!POOL.awaitTermination(120, TimeUnit.SECONDS)) {
                    POOL.shutdownNow();
                    if (!POOL.awaitTermination(120, TimeUnit.SECONDS)) {
                        log.info("pool did not termination");
                    }
                }
            } catch (InterruptedException e) {
                POOL.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}

