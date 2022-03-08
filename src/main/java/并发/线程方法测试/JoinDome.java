package 并发.线程方法测试;

import ch.qos.logback.core.util.Loader;
import lombok.extern.slf4j.Slf4j;

/**
 * join 测试
 * @author wanfeng
 * @create 2022/3/7 8:13
 * @package 并发.线程方法测试
 */
@Slf4j(topic="c")
public class JoinDome {
    private static int i = 0;
    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    i = 100;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join(1000);    // 若join的时间阻塞主线程不足以让线程完成i的赋值，则会赋值失败
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);


    }
}
