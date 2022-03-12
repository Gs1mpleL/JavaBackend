package 并发.设计模式;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * 模拟场景 线程1 等待线程2生产出一个list
 * @author wanfeng
 * @create 2022/3/11 16:43
 * @package 并发.设计模式
 */
@Slf4j(topic = "c")
public class GuardedObjectDome {
    public static void main(String[] args) {
        // 中间桥梁
        GuardedObject guardedObject = new GuardedObject();
        Thread t1 = new Thread(()-> {
            // 等待结果
            log.debug("等待线程2的list的产生");
            Object o = null;
            try {
                o = guardedObject.get(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(o == null){
                log.debug("等不及了 直接取消吧");
            }
            log.debug("接收到list" + o);
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("开始创建list");
            ArrayList<String> arr = new ArrayList<>();
            arr.add("是这样的!");
            arr.add("是这样的!");
            arr.add("是这样的!");
            arr.add("是这样的!");
            log.debug("创建结束!");
            guardedObject.complete(arr);
        });

        t1.start();
        t2.start();
    }
}


/**
 * 中间桥梁类
 */
class GuardedObject{
    /**
     * 中间结果
     */
    private Object response;

    /**
     * 获取结果 最大等大事件
     */
    public Object get(long timeout) throws InterruptedException {
        // 记录开始点
        long begin = System.currentTimeMillis();
        // 经历时间
        long pass = 0;
        synchronized (this){
            while (response == null){
                if(pass > timeout){
                    return null;
                }
                this.wait(timeout - pass);
                pass = System.currentTimeMillis() - begin;
            }

            return response;
        }
    }

    /**
     * 产生结果
     */
    public void complete(Object response){
        synchronized(this){
            this.response = response;
            this.notifyAll();
        }
    }
}