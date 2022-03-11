package 并发.锁机制;

import lombok.extern.slf4j.Slf4j;

/**
 * 休息和唤醒API
 * @author wanfeng
 * @create 2022/3/11 16:10
 * @package 并发.锁机制
 */
@Slf4j(topic = "c")
public class WaitAndNotifyDome {
    public static final Object lock = new Object();
    public static boolean food = false;
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            synchronized(lock){
                if(!food){
                    log.debug("没food 我不工作了");
                    // wait()把当前线程放入等待区  其他线程开始运行
                    try {
                        lock.wait();
//                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("我被唤醒了！！！有food了意思是 " + food);
            }
        });
        thread.start();
        for(int i = 0;i<5;i++){
            new Thread(()->{
                synchronized (lock){
                    log.debug("其他人干活");
                }
            }).start();
        }

        synchronized (lock){
            food = true;
            log.debug("设置food为" + true);
            lock.notify();
        }


    }
}
