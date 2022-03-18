package 并发.线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanfeng
 * @created 2022/3/18 16:28
 * @package 并发
 */
@Slf4j(topic = "c")
public class 手写一个线程池 {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(2,1000,TimeUnit.MILLISECONDS,4,(queue,task)->{
                queue.offer(task,100,TimeUnit.MILLISECONDS);
        });
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            pool.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("任务输出》》》》》》{}", finalI);
            });
        }
    }

}

/**
 * 拒绝策略
 */
@FunctionalInterface
interface RejectPolicy<T>{
    void reject(BlockingQueue<T>queue,T task);
}

@Slf4j(topic = "c")
class ThreadPool{
    /**
     * 任务队列
     */
    private BlockingQueue<Runnable>taskQueue;
    /**
     * 来的线程
     */
    private HashSet<Worker> workers= new HashSet<>();

    /**
     * 线程数
     */
    private int coreSize;

    /**
     * 线程超时时间
     */
    private long timeout;
    private TimeUnit timeUnit;

    /**
     * 拒绝策略
     */
    private RejectPolicy<Runnable>rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapacity,RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }


    /**
     * 执行任务
     */
    public void execute(Runnable runnable){
        synchronized (this){
            // 当任务数没有超过核心任务数时，交给worker执行
            if(workers.size() < coreSize){
                Worker worker = new Worker(runnable);
                log.debug("新增worker{}, {}",worker,runnable);
                workers.add(worker);
                worker.start();
            }else{
                taskQueue.tryPut(rejectPolicy,runnable);
            }
        }

    }



    class Worker extends Thread{
        private Runnable runnable;
        public Worker(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {
            // 当前方法不为空  或者任务队列不为空
            while (runnable!=null || (runnable = taskQueue.poll(1,TimeUnit.SECONDS)) != null ){
                // 执行任务
                // 当当前任务没有执行完成时
                try{
                    log.debug("正在执行任务......{}",runnable);
                    runnable.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    runnable = null;
                }
            }
            // 没任务可做，移除
            synchronized (workers){
                log.debug("worker被移除{}",this);
                workers.remove(this);
            }
        }
    }
}

/**
 * 阻塞队列，调和消费者和生产者
 * @param <T> 任务
 */
class BlockingQueue<T>{
    /**
     * 任务队列
     */
    private Deque<T> queue = new ArrayDeque<>();

    /**
     * 锁保护输入取出
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 消费者条件变量
     */
    private Condition emptyWaitSet = lock.newCondition();

    /**
     * 生产者条件变量
     */
    private Condition fullWaitSet = lock.newCondition();
    /**
     * 容量
     */
    private int capacity;

    BlockingQueue(int capacity) {
        this.capacity = capacity;
    }
    /**
     * 阻塞添加任务到队列
     */
    public boolean offer(T task,long timeout,TimeUnit timeUnit){
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity){
                try {
                    if(nanos <= 0){
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 添加进入队列
            queue.addLast(task);
            // 唤醒等待的消费者进程
            emptyWaitSet.signal();
            return true;
        }finally {
            lock.unlock();
        }
    }
    /**
     * 超时等待的阻塞获取
     */
    public T poll(long timeout, TimeUnit timeUnit){
        lock.lock();
        try{
            // 统一转换程纳秒
            long nanos = timeUnit.toNanos(timeout);

            // 线程想获得任务，首先判断队列是否为空，空就进入休息室等待
            while(queue.isEmpty()){
                try {
                    // 已经超时 返回null
                    if(nanos<=0){
                        return null;
                    }
                    // 如果在等待时被打断，发现还是需要等待，就会把nanos修改成剩余等待时间，而不是重新进行等待
                    nanos = emptyWaitSet.awaitNanos(timeout);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            // 等待结束，获取
            T t =  queue.removeFirst();
            // 这时候就不满了队列，唤醒等待中的生产者
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }
    /**
     * 阻塞获得
     */
    public T take(){
        lock.lock();
        try{
            // 线程想获得任务，首先判断队列是否为空，空就进入休息室等待
            while(queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            // 等待结束，获取
            T t =  queue.removeFirst();
            // 这时候就不满了队列，唤醒等待中的生产者
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞添加
     */
    public void put(T element){
        lock.lock();
        try {
            while (queue.size() == capacity){
                try {
                    fullWaitSet.await();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 添加进入队列
            queue.addLast(element);
            // 唤醒等待的消费者进程
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     *  获取大小
     */
    public int size(){
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 拒绝策略
     * @param rejectPolicy 策略接口
     * @param runnable 执行的工作
     */
    public void tryPut(RejectPolicy<T> rejectPolicy, T runnable) {
        lock.lock();
        try {
            // 判断队列是否已经满了
            if(queue.size() == capacity){
                // 具体如何执行 看接口如何实现
                rejectPolicy.reject(this,runnable);
            }else{
                queue.addLast(runnable);
                emptyWaitSet.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}