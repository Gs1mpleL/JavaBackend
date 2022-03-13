package 并发.可重入锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁可打断
 * @author wanfeng
 * @create 2022/3/13 14:03
 * @package 并发.可重入锁
 */
@Slf4j(topic = "c")
public class ReentrantLockInterruption {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{

            try {
                log.debug("尝试获得锁了");
                // 如果没有竞争就进
                // 有竞争就进入阻塞队列，可以被其他线程interrupt打断
                reentrantLock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.debug("没有获得锁了");

                e.printStackTrace();
                return;
            }

            try {
                log.debug("获得锁了");
            }finally {
                reentrantLock.unlock();
            }
        },"t1");

        // 主线程加锁
        reentrantLock.lock();
        t1.start();

        Thread.sleep(1000);
        log.debug("主线程打断t1");
        t1.interrupt();
    }
}
