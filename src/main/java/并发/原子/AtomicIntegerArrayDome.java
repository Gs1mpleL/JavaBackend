package 并发.原子;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子数组 可以保证数组元素的安全性
 * @author wanfeng
 * @created 2022/3/18 14:59
 * @package 并发.原子
 */
public class AtomicIntegerArrayDome {
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(100);

    public static void main(String[] args) {
        System.out.println(atomicIntegerArray.length());
    }
}
