package 并发.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wanfeng
 * @created 2022/3/18 19:38
 * @package 并发.线程池
 */
public class CacheThreadPoolDome {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool1 = Executors.newSingleThreadExecutor();
    }
}
