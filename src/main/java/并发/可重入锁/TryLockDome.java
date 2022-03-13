package 并发.可重入锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanfeng
 * @create 2022/3/13 14:14
 * @package 并发.可重入锁
 */
@Slf4j(topic = "c")
public class TryLockDome {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            boolean b = false;
            try {
                b = lock.tryLock(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("尝试获得锁{}",b);
        });

        lock.lock();
        t1.start();
        Thread.sleep(1000);
        lock.unlock();
    }
}
