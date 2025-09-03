package Day5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchExample2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 等待开始信号");
                    startSignal.await();
                    System.out.println(Thread.currentThread().getName() + " 开始跑步");
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println(Thread.currentThread().getName() + " 到达终点");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    doneSignal.countDown();
                }
            });
        }

        Thread.sleep(1000);
        System.out.println("裁判发出开始信号");
        startSignal.countDown();
        doneSignal.await();
        System.out.println("所有选手到达终点，比赛结束");
        service.shutdown();
    }
}
