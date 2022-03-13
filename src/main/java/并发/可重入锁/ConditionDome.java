package 并发.可重入锁;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件变量
 * @author wanfeng
 * @create 2022/3/13 14:33
 * @package 并发.可重入锁
 */
public class ConditionDome {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 创建一个条件变量 (WaitSet)
        Condition condition = reentrantLock.newCondition();
        reentrantLock.lock();
        // 线程进入等待
        condition.await();

        // 其他线程利用休息室 来唤醒线程
        condition.signal();
    }
}
