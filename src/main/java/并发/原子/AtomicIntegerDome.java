package 并发.原子;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子整数    内部维护一个     private volatile int value;
 * @author wanfeng
 * @created 2022/3/18 14:19
 * @package 并发.原子
 */
public class AtomicIntegerDome {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // ++i
        int i = atomicInteger.incrementAndGet();
        // i++
        int andIncrement = atomicInteger.getAndIncrement();

        // 获取并且增加自定义值
        int i1 = atomicInteger.addAndGet(100);

        System.out.println(i1);
        System.out.println(andIncrement);
        System.out.println(i);


        // 原子的更新数据并获取
        int i2 = atomicInteger.updateAndGet(x -> x * 10);
        System.out.println(i2);

    }
}
