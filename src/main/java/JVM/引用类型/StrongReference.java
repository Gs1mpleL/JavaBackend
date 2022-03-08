package JVM.引用类型;

/**
 * 强引用，java虚拟机宁可抛出OOM异常，也不会回收具有强引用的对象来释放内存
 * 我们可以将对象显示的赋值为null，则gc认为该对象不存在引用，这时就可以回收这个对象
 * @author wanfeng
 * @created 2022/3/5 15:18
 * @package JVM.应用类型
 */
public class StrongReference {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
