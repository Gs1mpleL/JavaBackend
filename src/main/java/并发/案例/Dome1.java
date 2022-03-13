package 并发.案例;

/**
 * 3个线程交替输出 要求输出后为abcabcabc
 * @author wanfeng
 * @create 2022/3/13 15:09
 * @package 并发.案例
 */
public class Dome1 {
    // 锁
    private static final Object lock = new Object();
    private static String printFlag = "a";
    private static void print(String printNow, String printNext) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            synchronized(lock){
                while (!printNow.equals(printFlag)){
                    lock.wait();
                }
                System.out.print(printNow);
                printFlag = printNext;
                lock.notifyAll();
            }

        }
    }
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                print("a","b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                print("b","c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                print("c","a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
