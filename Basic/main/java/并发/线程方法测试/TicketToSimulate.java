package 并发.线程方法测试;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * 模拟多线程买票
 * @author wanfeng
 * @created 2022/3/7 19:03
 * @package 并发.线程方法测试
 */
@Slf4j(topic = "c")
public class TicketToSimulate {
    // 模拟多线程买票
    public static void main(String[] args) throws InterruptedException {
        // 共享的买票窗口 1000张票
        TicketWindow window = new TicketWindow(1000);
        // 存储卖出的
        List<Integer>list = new Vector<>();
        // 存储所以线对象
        ArrayList<Thread>threadArrayList = new ArrayList<>();
        // 模拟2000人来买票
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                list.add(window.sell(getRandomNumber()));
            });
            threadArrayList.add(thread);
            thread.start();
        }
        // 等待所有线程结束
        for(Thread t:threadArrayList){
            t.join();
        }

        // 统计余票
        int count = window.getCount();
        log.debug("余票:{}",count);
        // 统计卖出的票
        log.debug("卖出:{}",list.stream().mapToInt(i->i).sum());
    }


    // 只是获取操作 线程安全
    static Random random = new Random();
    public static int getRandomNumber(){
        return random.nextInt(5) + 1;
    }

}
class TicketWindow{
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    // 获取余票
    public int getCount(){
        return this.count;
    }

    // 售票 是线程不安全的 需要在操作时锁住this 防止其他线程sell
    public synchronized int sell(int num){
        if(this.count >= num){
            count = count - num;
            return num;
        }else{
            return 0;
        }
    }
}
