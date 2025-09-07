package Day8.CompetebaleLock;

import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args)
    {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

    static class Phone{
        public void sendSMS() throws Exception
        {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + ":开始执行");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + ":结束执行");
            }finally{
                lock.unlock();
            }
        }
        public void sendEmail() throws Exception
        {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + ":开始执行");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + ":结束执行");
            }finally{
                lock.unlock();
            }
        }
    }
}
