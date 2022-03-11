package 并发.锁机制;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 查看Object的对象头
 * @author wanfeng
 * @created 2022/3/9 11:19
 * @package 并发.锁机制
 */
@Slf4j(topic = "c")
public class GetObjectMarkWordDome {
    public static void main(String[] args) throws InterruptedException {
        // -XX:-UseBiasedLocking 禁用偏向锁
        Dog d = new Dog();
        // 创建后
        log.debug(ClassLayout.parseInstance(d).toPrintable());
        Thread.sleep(1000);
        // 调用hashCode会产生hash码,会覆盖偏向码的那一位,因为没地方存储了
        int i = d.hashCode();
        // 加锁后
        synchronized (d){
            Thread.sleep(1000);
            log.debug("加锁后" + ClassLayout.parseInstance(d).toPrintable());
        }
        Thread.sleep(1000);
        // 解锁后
        log.debug(ClassLayout.parseInstance(d).toPrintable());
    }
}
class Dog{

}
