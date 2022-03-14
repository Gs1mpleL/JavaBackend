package 并发.TMP;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanfeng
 * @created 2022/3/14 18:22
 * @package 并发.TMP
 */
@Slf4j(topic = "c")
public class MoneyDome {
    private static AtomicInteger money = new AtomicInteger(10000);

    public static void test(){
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(() -> {
                while (true){
                    int prev = money.get();
                    int now =prev - 10;
                    // compareAndSet(原子的进行比较和设置)
                    if(money.compareAndSet(prev,now)){
                        break;
                    }
                }

            });
            list.add(t);
        }
        list.forEach(Thread::start);
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(money.get());

    }
    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
