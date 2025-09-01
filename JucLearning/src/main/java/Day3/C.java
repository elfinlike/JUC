package Day3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class C {
    public static void main(String[] args) {
        DataC data = new DataC();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
         new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}

class DataC {//资源类
    final ReentrantLock lock = new ReentrantLock();
    private int number = 0;
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void printA() throws InterruptedException {
        lock.lock();
        while (number != 0) {
            c1.await();
        }
        System.out.println("A");
        number = 1;
        c2.signalAll();
        lock.unlock();
    }

    public void printB() throws InterruptedException {
        lock.lock();
        while (number != 1) {
            c2.await();
        }
        System.out.println("B");
        number = 2;
        c3.signalAll();
        lock.unlock();
    }

    public void printC() throws InterruptedException {
        lock.lock();
        while (number != 2) {
            c3.await();
        }

        System.out.println("C");
        number = 0;
        c1.signalAll();
        lock.unlock();
    }

}