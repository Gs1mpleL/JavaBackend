package 并发.JUC包;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author wanfeng
 * @create 2022/3/21 19:25
 * @package 并发.JUC包
 */
public class ForkJoinDome {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        System.out.println(pool.invoke(new MyTask(100)));
    }
}

class MyTask extends RecursiveTask<Integer>{
    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if(n == 1){
            return 1;
        }
        MyTask t1 =  new MyTask(n-1);
        t1.fork();
        return n += t1.join();
    }
}
