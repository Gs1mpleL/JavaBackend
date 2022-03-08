package 并发.线程方法测试;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author wanfeng
 * @create 2022/3/6 20:23
 * @package 并发
 */
// topic 是 日志打印默认添加的内容
@Slf4j(topic = "c")
public class CreateThreadsDome {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程对象
        Thread t1 = new Thread(){
            @Override
            public void run() {
                log.debug("running!");
            }
        };
        t1.setName("t1");
        t1.start();

        // 实现Runnable接口当参数传入Thread
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                log.debug("running!");
            }
        };
        Thread t2 = new Thread(r1);
        t2.setName("t2");
        t2.start();

        // lambda来创建runnable
        Runnable r2 = () -> {log.debug("running!");};
        Thread t3 = new Thread(r2);
        t3.setName("t3");
        t3.start();

        // FutureTask创建线程
        FutureTask<Integer> task = new FutureTask(()->{
            log.debug("running!");
            return 100;
        });
        new Thread(task,"t4").start();
        // 在需要的线程中来获取FutureTask的结果
        Integer result = task.get();


        // 主线程
        log.debug("running!");
    }
}
