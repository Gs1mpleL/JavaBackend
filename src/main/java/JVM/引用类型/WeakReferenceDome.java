package JVM.引用类型;

import java.lang.ref.WeakReference;

/**
 *  通过WeakReference创建的对象就是一个弱引用
 * 只有进行了垃圾回收，弱引用的对象就会被回收，弱引用生命周期最长就是两次GC的间隔时间
 * @author wanfeng
 * @created 2022/3/5 15:24
 * @package JVM.引用类型
 */
public class WeakReferenceDome {
    public static void main(String[] args) {
        WeakReference<String> weakReference = new WeakReference<>(new String("Hello WeakReference"));
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get()   + "通过一次gc后已经变成了null");
    }
}
