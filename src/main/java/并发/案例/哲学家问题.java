package 并发.案例;

import lombok.extern.slf4j.Slf4j;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author wanfeng
 * @create 2022/3/22 16:34
 * @package 并发.案例
 */
@Slf4j(topic = "c")
public class 哲学家问题 {
    public static void main(String[] args) {
        // 5根筷子 5个哲学家
        Chopstick c1 = new Chopstick();
        Chopstick c2 = new Chopstick();
        Chopstick c3 = new Chopstick();
        Chopstick c4 = new Chopstick();
        Chopstick c5 = new Chopstick();

        Zxj zxj1 = new Zxj(1,c1,c2);
        Zxj zxj2 = new Zxj(2,c2,c3);
        Zxj zxj3 = new Zxj(3,c3,c4);
        Zxj zxj4 = new Zxj(4,c4,c5);
        Zxj zxj5 = new Zxj(5,c5,c1);

        // 解法1:一个人先去获得右手边筷子，再回来获取左手边筷子
        // Zxj zxj5 = new Zxj(5,c1,c5);

        zxj1.start();
        zxj2.start();
        zxj3.start();
        zxj4.start();
        zxj5.start();
    }
}
@Slf4j(topic = "c")
class Zxj extends Thread{
    private final int index;
    private final Chopstick left;
    private final Chopstick right;

    public Zxj(int index, Chopstick left, Chopstick right) {
        this.index = index;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        synchronized (left){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("{}->获得了左手边筷子,开始等待右手边的筷子",this.index);
            synchronized (right){
                log.debug("{}->获得了右手边筷子",this.index);
                log.debug("{}->正在吃饭",this.index);
            }
        }
    }
}
class Chopstick{
}
