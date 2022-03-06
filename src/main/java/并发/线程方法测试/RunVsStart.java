package 并发.线程方法测试;

import lombok.extern.slf4j.Slf4j;

/**
 * run方法和start方法的比较
 * @author wanfeng
 * @create 2022/3/6 21:19
 * @package 并发
 */
@Slf4j(topic = "c")
public class RunVsStart {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        // 直接指向run  就相当于在主线程中执行
        t1.run();

        // 只有直接调用start()方法才能创建新的线程来执行操作
        t1.start();
    }
}
