package JVM.垃圾回收;

/**
 * 对象可以在被GC的时候自救一次
 * 这种自救只有一次，因为，一个对象的finalize（）方法最多只会被系统自动调用一次
 * @author wanfeng
 * @created 2022/3/5 18:31
 * @package JVM.垃圾回收
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive(){
        System.out.println("I am alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("invoke finalize()");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        // null后被执行finalize（）方法来回收
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        // 这里可以看到，SAVE_HOOK并没有被回收，因为重写了finalize（）方法，内部SAVE_HOOK起了作用，逃离了回收
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("I am dead!");
        }


        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        // 这里已经被回收了，因为系统最多调用一次finalize（）方法
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("I am dead!");
        }
    }
}
