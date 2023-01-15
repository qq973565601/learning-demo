package cn.lzx.juc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池，控制资源
 * 线程池7个参数
 * 1. corePoolSize：核心线程数，线程池创建好后准备就绪的线程数量，等待来接受异步异步任务去执行。
 * 一直存在，可设置允许超时，allowCoreThreadTimeOut
 * 2. maximumPoolSize：最大线程数量，控制运行
 * 3. keepAliveTime：存活时间，如果当前线程数量大于核心线程数量，释放空闲的线程，只要线程空闲大于指定的keepAliveTime
 * 释放超出的数量【maximumPoolSize-keepAliveTime】
 * 4. unit：时间单位
 * 5. BlockingQueue<Runnable> workQueue：阻塞队列。如果任务有很多，就会将多余任务放置队列里，只要与线程空闲，就会去队列里去新的任务
 * 6. threadFactory：线程创建工厂
 * 7. handler：处理满队列，如果队列满了，按照我们指定的拒绝策略RejectedExecutionHandler拒绝执行任务
 * <p>
 * 工作顺序：
 * 1）. 线程池创建，准备好core数量的核心线程，准备接受任务
 * 1.1）. core满了，将再进来的任务放入阻塞队列里，。空闲的core就会自己去阻塞队列获取任务执行
 * 1.2）. 阻塞队列满了，就直接开新线程执行，最大只能开到maximumPoolSize指定的数量
 * 1.3）. max满了就执行拒绝策略
 * 1.4）. max都执行完成，有很多空闲，在指定的时间keepAliveTime以后，释放max-core这些线程
 * <p>
 * 问题：一个线程池，core 7，max 20，queue 50，100并发进来怎么分配
 * 7个立即执行，50个进入队列，再开13个进行执行，剩下30个使用拒绝策略执行
 *
 * @author lzx
 * @since 2022-05-23
 */
public class ThreadPoolTest {
    /**
     * 工具类创建一个线程池
     */
    public static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        System.out.println("main...start...");
        service.execute(new RunAbleTest.Runnable01());
        System.out.println("main...end...");
    }
}
