package Day7.Volatile;

import java.util.concurrent.TimeUnit;

public class Demo2 {
    // volatile不保证原子性
    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++)
            new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    add();
            }).start();
        if (Thread.activeCount() > 2) {
            Thread.yield();//yield让出计算资源并重新竞争资源
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println(num);
    }

    public synchronized static void add() {
        num += 1;
    }
}
