package 并发.可重入锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author wanfeng
 * @create 2022/3/13 13:56
 * @package 并发.可重入锁
 */
@Slf4j(topic = "c")
public class ReEntrantLockDome {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 加锁
        lock.lock();
        try{
            // 再次加锁
            m1();
        }finally {
            // 解锁
            lock.unlock();
        }
    }

    /**
     * 可重入
     */
    public static void m1(){
        // 加锁
        lock.lock();
        try{
            log.debug("进入m1");
        }finally {
            // 解锁
            lock.unlock();
        }
    }
}
