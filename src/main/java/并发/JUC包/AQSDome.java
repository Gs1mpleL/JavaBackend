package 并发.JUC包;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanfeng
 * @create 2022/3/21 19:48
 * @package 并发.JUC包
 */
@Slf4j(topic = "c")
public class AQSDome {
    public static void main(String[] args) {
        BufferedReader reader;
        MyLock myLock = new MyLock();
        new Thread(() -> {
            myLock.lock();
            myLock.lock();
            try {
                log.debug("枷锁成功1");
            }finally {
                log.debug("解锁");
                myLock.unlock();
            }
        }).start();

        new Thread(() -> {
            myLock.lock();
            try {
                log.debug("枷锁成功1");
            }finally {
                log.debug("解锁");
                myLock.unlock();
            }
        }).start();
    }

    ReentrantLock reentrantLock = new ReentrantLock();
}


// 自定义锁
class MyLock implements Lock{


    class MySync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0,1)) {
                // 成功加锁
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }


        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }


        @Override  // 是否持有这把锁
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    private MySync mySync = new MySync();


    @Override // 加锁
    public void lock() {
        mySync.acquire(1);
    }

    @Override // 可打断加锁
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);
    }

    @Override // 尝试加锁
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override // 尝试加锁，超时等待
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
       return mySync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override // 解锁
    public void unlock() {
        mySync.release(1);
    }

    @Override // 创建
    public Condition newCondition() {
        return mySync.newCondition();
    }
}
