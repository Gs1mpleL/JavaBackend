package JVM.引用类型;

import java.lang.ref.SoftReference;

/**
 * 通过SoftReference创建的对象就是一个弱引用
 * 只要JVM内存充足，无论发生多少次GC，软引用只想的对象都不会被回收，但是如果JVM内存满了，就会被回收
 * 软引用如果没有被回收，就可以一直被访问
 * @author wanfeng
 * @created 2022/3/5 15:23
 * @package JVM.引用类型
 */
public class SoftReferenceDome {
    public static void main(String[] args) {
        // 创建软引用
        SoftReference<String> softReference = new SoftReference<>(new String("hello SoftReference"));
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get() + "通过一次gc后因为JVM内存并不紧张，所以不会被清理掉");
    }
}
