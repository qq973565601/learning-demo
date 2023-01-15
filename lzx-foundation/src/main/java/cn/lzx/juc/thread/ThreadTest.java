package cn.lzx.juc.thread;

/**
 * 多线程
 *
 * @author lzx
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println("main...start...");

        Thread01 thread01 = new Thread01();
        thread01.start();
        System.out.println("main...end...");

    }

    /**
     * 继承thread类
     */
    public static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
    }
}
