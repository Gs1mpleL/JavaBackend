package 并发.原子;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题
 * 无法感知他的修改  其他线程可能先更改后又修改回来
 * @author wanfeng
 * @created 2022/3/18 14:42
 * @package 并发.原子
 */
public class ABADome {
    private static  AtomicReference<String> ref1 = new AtomicReference<>("A");
    /**
     * 带时间戳的原子类，可以感知其他线程对ref的修改 因为需要传入当前的时间戳 可以知道该过多少次
     */
    private static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A",0);
    /**
     * 带标记的原子类，可以看是否被更改
     */
    private static AtomicMarkableReference<String> reference = new AtomicMarkableReference<>("A",true);




    public static void main(String[] args) throws InterruptedException {
        String prev =  ref.getReference();
        int stamp = ref.getStamp();
        System.out.println(stamp + "<- stamp");
        String next = "B";
        other();
        Thread.sleep(1000);
        System.out.println("主线程 A->B \t" + ref.compareAndSet(prev,next,stamp,stamp+1));
    }


    public static void other(){
        new Thread(() -> {
            int stamp = ref.getStamp();

            System.out.println("线程 A->B\t" + ref.compareAndSet("A","B",stamp,stamp+1));
            System.out.println("线程 B->A\t" + ref.compareAndSet("B","A",stamp+1,stamp+2));
        }).start();
    }
}
