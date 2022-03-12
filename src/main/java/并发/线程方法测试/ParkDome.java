package 并发.线程方法测试;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wanfeng
 * @created 2022/3/11 18:33
 * @package 并发.线程方法测试
 */
@Slf4j(topic = "c")
public class ParkDome {
    public static void main(String[] args) {
        Thread t1  = new Thread(() -> {
            log.debug("t1 running!");
            // park住了
            LockSupport.park();
            log.debug("unpark 成功");
        }, "t1");

        t1.start();
        // unpark!
        LockSupport.unpark(t1);
    }
}
