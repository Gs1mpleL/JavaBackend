package 并发.线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanfeng
 * @created 2022/3/18 19:29
 * @package 并发.线程池
 */
@Slf4j(topic = "c")
public class FixedThreadPoolDome {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger a  = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"myName" + a.getAndIncrement());
            }
        });



        ScheduledExecutorService pool2 = Executors.newScheduledThreadPool(2);
        pool2.scheduleAtFixedRate(()->{
            System.out.println("我真的回写");
        },3,3,TimeUnit.SECONDS);




        pool.execute(() -> {
            log.debug("running!");
        });


        Future<String> submit = pool.submit(() -> {
            log.debug("callable running!");
            return "ok";
        });
        System.out.println(submit.get());
    }
}
