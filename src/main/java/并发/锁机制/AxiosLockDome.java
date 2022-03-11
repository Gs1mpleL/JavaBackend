package 并发.锁机制;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁
 * 1.一个线程默认会是偏向锁，线程ID刚开始是0
 * 2.当这个线程加锁时，会向Object中添加线程ID
 * 3.其他线程请求锁时，会发现该Object的线程ID不属于自己
 * 4.撤销偏向锁，改为轻量级锁
 * @author wanfeng
 * @created 2022/3/11 12:20
 * @package 并发.锁机制
 */
@Slf4j(topic = "c")
public class AxiosLockDome {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Thread t1 = new Thread(() -> {
            log.debug(ClassLayout.parseInstance(dog).toPrintable());
            synchronized (dog) {
                log.debug(ClassLayout.parseInstance(dog).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(dog).toPrintable());
        });
        t1.start();

        new Thread(() -> {
            try {
                // 先让t1结束
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug(ClassLayout.parseInstance(dog).toPrintable());
            synchronized(dog){
                log.debug(ClassLayout.parseInstance(dog).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(dog).toPrintable());
        }).start();

    }
}
