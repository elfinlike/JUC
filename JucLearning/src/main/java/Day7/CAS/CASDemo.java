package Day7.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger num=new AtomicInteger(0);
        /**
         * 期望、更新
         * public final boolean compareAndSet(int expectedValue, int newValue)
         * 如果期望的值达到了，那么就更新，否则不更新
         * CAS,是CPU的并发原语；
         */
        System.out.println(num.compareAndSet(0, 2));
        System.out.println(num.get());
        System.out.println(num.compareAndSet(0, 1));
        System.out.println(num.get());

    }
}
