package 并发.案例;

import java.util.ArrayList;

/**
 * 最慢排序
 * @author wanfeng
 * @create 2022/3/16 14:15
 * @package 并发.案例
 */
public class Dome2 implements Runnable{
    private int num;

    public Dome2(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(num);
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] arr = {22,3,1,5,77,4,2};
        for(int x:arr){
            new Thread(new Dome2(x)).start();
        }

    }
}
