package cn.lzx.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lzx
 */
public class CallAbleTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main...start...");
        // 配合FutureTask使用
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
        new Thread(futureTask).start();

        // 阻塞等待线程执行完，获取返回结果
        Integer integer = futureTask.get();
        System.out.println(integer);
        System.out.println("main...end...");


    }

    /**
     * 实现callable接口,允许返回一个值
     */
    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }
    }
}
