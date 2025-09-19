package Day7.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo3 {
    private volatile static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            new Thread(()->{
                for (int j = 0; j < 1000; j++)
                    add();
            }).start();

        while (Thread.activeCount()>2)
            Thread.yield();
        System.out.println(count);
    }
    public static void add()
    {
        count.getAndIncrement();//AtomicInteger +1方法；不是简单的+1操作，而是使用的CAS算法
    }
}
