package JVM.垃圾回收;

import java.util.ArrayList;

/**
 * 模拟垃圾回收过程
 * @author wanfeng
 * @create 2022/3/5 20:00
 * @package JVM.垃圾回收
 */
public class MinorGCDome {
    // -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
    // 初始堆空间 最大堆空间 新生代空间 垃圾回收器 打印GC详情
    public static void main(String[] args) {
        ArrayList<byte[]>list = new ArrayList<>();
        // 添加7Mb后新生代的伊甸区满，触发垃圾回收，对象通过复制算法进入To并转换成From
        list.add(new byte[7*1024*1024]);
        // 第二次 新生代满，会尝试放入老年代中
        list.add(new byte[1024*1024]);

        // 若直接添加一个大小大于新生代的对象，就会直接放入老年代而不触发GC
        list.add(new byte[8*1024*1024]);
    }

    /*
      第一次GC:
      Heap
       def new generation   total 9216K, used 8114K [0x04800000, 0x05200000, 0x05200000)
        eden space 8192K,  92% used [0x04800000, 0x04f66840, 0x05000000)
        from space 1024K,  52% used [0x05100000, 0x05185fd0, 0x05200000)  部分对象进入了from区
        to   space 1024K,   0% used [0x05000000, 0x05000000, 0x05100000)
       tenured generation   total 10240K, used 0K [0x05200000, 0x05c00000, 0x05c00000)
         the space 10240K,   0% used [0x05200000, 0x05200000, 0x05200200, 0x05c00000)
       Metaspace       used 140K, capacity 2280K, committed 2368K, reserved 4480K

       第二次GC:
       Heap
        def new generation   total 9216K, used 1116K [0x05200000, 0x05c00000, 0x05c00000)
        eden space 8192K,  13% used [0x05200000, 0x05314938, 0x05a00000)
        from space 1024K,   0% used [0x05a00000, 0x05a02710, 0x05b00000)
        to   space 1024K,   0% used [0x05b00000, 0x05b00000, 0x05c00000)
        tenured generation   total 10240K, used 7704K [0x05c00000, 0x06600000, 0x06600000)
        the space 10240K,  75% used [0x05c00000, 0x06386250, 0x06386400, 0x06600000)  直接把大对象放入了老年代
        Metaspace       used 144K, capacity 2280K, committed 2368K, reserved 4480K
     */
}
