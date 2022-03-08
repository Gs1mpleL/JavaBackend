package 并发.线程方法测试;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wanfeng
 * @create 2022/3/7 8:44
 * @package 并发.线程方法测试
 */
@Slf4j(topic = "c")
public class InterruptDome {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleep");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // 如果线程被打断，会让sleep方法抛出这个错误
                e.printStackTrace();
            }
        });
        t1.start();
        // 只是设置中断标志位的状态，线程仍 会继续运行
        // 如果已经阻塞，会打断阻塞状态并抛出对应的异常
        t1.interrupt();
        log.debug("打断后 flag = "+String.valueOf(t1.isInterrupted()));
    }
}
