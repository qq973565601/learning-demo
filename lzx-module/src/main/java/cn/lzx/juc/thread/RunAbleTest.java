package cn.lzx.juc.thread;

/**
 * @author lzx
 */
public class RunAbleTest {
    public static void main(String[] args) {
        System.out.println("main...start...");
        Runnable01 runnable01 = new Runnable01();
        new Thread(runnable01).start();
        System.out.println("main...end...");


    }

    /**
     * 实现runnable接口
     */
    public static class Runnable01 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
    }
}
