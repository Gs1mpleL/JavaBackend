package 并发.可重入锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanfeng
 * @create 2022/3/13 14:55
 * @package 并发.可重入锁
 */
@Slf4j(topic = "c")
public class 访问顺序调控 {
    private static final Object lock = new Object();
    static boolean isT2Run = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                while (!isT2Run){
                    log.debug("仍在等待");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("t2执行完了 开始t1");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock){
                log.debug("2执行");
                isT2Run = true;
                // 唤醒2
                lock.notify();
            }
        });
        t1.start();
        t2.start();
    }


}
