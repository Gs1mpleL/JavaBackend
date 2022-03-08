package 并发.线程方法测试;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wanfeng
 * @created 2022/3/7 12:25
 * @package 并发.线程方法测试
 */
@Slf4j(topic = "c.TwoPhaseTerminal")
public class TwoPhaseTerminal {
    // 监视器线程
    private Thread monitor;

    /**
     * 监视器启动
     */
    public void start(){
        monitor = new Thread(() -> {
            while (true){
                log.debug("monitor running....");
                // 正常运行时接收到了打断信号
                if(Thread.currentThread().isInterrupted()){
                    log.debug("结束前的一些操作");
                    break;
                }
                // 监视器休息3秒
                try {
                    Thread.sleep(500);
                    log.debug("休息过程中记录log....");
                } catch (InterruptedException e) {
                    // 如果在sleep时打断  会让标志位清空
                    // 所以要重新设置标志位，让再次循环时结束
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

        });
        monitor.start();
    }

    /**
     * 监视器关闭
     */
    public void stop(){
        // 发送线程打断信号   而不是直接stop线程
        monitor.interrupt();
    }


    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTerminal  twoPhaseTerminal = new TwoPhaseTerminal();
        twoPhaseTerminal.start();
        Thread.sleep(1000);
        twoPhaseTerminal.stop();
    }
}
