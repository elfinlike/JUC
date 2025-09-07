package Day8.CASLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Demo1 {
    AtomicReference<Thread> atomicStampedReference = new AtomicReference<>(null);

    public void myLock() {
        Thread thread = Thread.currentThread();
        while (!atomicStampedReference.compareAndSet(null, thread)) {

        }
        System.out.println(thread.getName() + "==>mylock");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicStampedReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "==>myUnlock");
    }

    public static void main(String[] args) throws InterruptedException {
        Demo1 lock = new Demo1();
        new Thread(() -> {
            lock.myLock();
            System.out.println(Thread.currentThread().getName() + "==>执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.myUnLock();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            lock.myLock();
            System.out.println(Thread.currentThread().getName() + "==>执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.myUnLock();
        }, "B").start();
    }

}
