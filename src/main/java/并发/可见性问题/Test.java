package 并发.可见性问题;

import lombok.extern.slf4j.Slf4j;

/**
 * 可见性问题的演示
 * @author wanfeng
 * @create 2022/3/13 19:48
 * @package 并发.可见性问题
 */
@Slf4j(topic = "c")
public class Test {
    private static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (run){
                // log.debug("run!!!");
            }
            log.debug("发现了修改");
        }).start();

        Thread.sleep(3000);
        run = false;
        log.debug("run已修改!");
    }
}
