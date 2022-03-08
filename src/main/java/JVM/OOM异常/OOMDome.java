package JVM.OOM异常;

import java.util.ArrayList;

/**
 * 测试堆内存溢出
 * @author wanfeng
 * @create 2022/3/6 14:45
 * @package JVM.OOM异常
 */
public class OOMDome {
    //-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError 堆最大最小都设置成20M，防止自动拓展， 后边是发生OOM时DUMP出当前状态到文件
    public static void main(String[] args) {
        // 一直往堆中添加对象
        ArrayList<Object> list = new ArrayList<>();
        while(true){
            list.add(new Object());
        }

        // java.lang.OutOfMemoryError: Java heap space
        // Dumping heap to java_pid12964.hprof ...
        // Heap dump file created [28170671 bytes in 0.196 secs]
    }
}
