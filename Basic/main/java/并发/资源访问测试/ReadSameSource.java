package 并发.资源访问测试;

import lombok.extern.slf4j.Slf4j;

/**
 * 两个线程同时获取某个资源出现的情况
 * @author wanfeng
 * @created 2022/3/7 13:19
 * @package 并发.资源访问测试
 */
@Slf4j(topic = "c")
public class ReadSameSource {
    private static Integer num = 0;
    private static Room lock = new Room();
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.add();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.sub();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(lock.getNum());
    }

    static class Room{
        private int count;
        public synchronized void add(){
            count++;
        }
        public void sub(){
            synchronized(this){
                count--;
            }
        }
        public int getNum(){
            synchronized (this){
                return count;
            }
        }
    }
}
