package 并发.线程方法测试;

import lombok.extern.slf4j.Slf4j;

/**
 * 多线程案例
 * @author wanfeng
 * @created 2022/3/7 13:00
 * @package 并发.线程方法测试
 */
@Slf4j(topic = "c.Dome")
public class ThreadSumDome {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
                    //
                   log.debug("洗水壶");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("烧开水");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "小王");

        Thread t2 = new Thread(() -> {
            //
            log.debug("洗茶壶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("洗茶杯");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("拿茶叶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 这里join就是等待t1的结束才能继续下去
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("最终事件");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "小五");

        t1.start();
        t2.start();
    }
}
