package JVM.引用类型;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用的创建多了一个依赖的队列，无法通过虚引用获取对象
 * 虚引用和前面的软引用、弱引用不同，它并不影响对象的生命周期。如果一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收
 * 虚引用一般来检测对象是否被回收
 * 无法通过虚引用获取对象
 * @author wanfeng
 * @created 2022/3/5 15:28
 * @package JVM.引用类型
 */
public class PhantomReferenceDome {
    public static void main(String[] args) {
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        PhantomReference<String> phantomReference = new PhantomReference<>(new String("Hello PhantomReference"), referenceQueue);
    }
}
