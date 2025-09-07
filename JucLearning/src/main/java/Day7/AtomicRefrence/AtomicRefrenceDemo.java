package Day7.AtomicRefrence;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicRefrenceDemo {
    // 原子引用 CAS 比较并交换，通过版本号、以及值的比较来进行交换
    // AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题
    // 和乐观锁的原理相同
    public static void main(String[] args) {
        AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(2020, 1);
        new Thread(() -> {
            System.out.println(asr.compareAndSet(100, 101, asr.getStamp(), asr.getStamp() + 1));
            System.out.println(asr.getStamp());
            System.out.println(asr.getReference());
        }).start();

        new Thread(() -> {
            System.out.println(asr.compareAndSet(101, 100, asr.getStamp(), asr.getStamp() + 1));
            System.out.println(asr.getStamp());
            System.out.println(asr.getReference());
        }).start();
    }
}
