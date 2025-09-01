package Day3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B {
    public static void main(String[] args) {
        newData data = new newData();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "D").start();

    }


}

class newData {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    private int number = 0;

    public void increase() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                notFull.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                notEmpty.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
