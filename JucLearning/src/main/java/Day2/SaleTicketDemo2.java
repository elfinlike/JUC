package Day2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 真正的多线程开发，降低耦合性
 * 线程是一个单独的资源类，没有任何附属的操作；
 * 1.属性，方法
 */
public class SaleTicketDemo2 {
    public static void main(String[] args) {

        //并发：多线程操作同一个资源类；
        Ticket02 ticket= new Ticket02();
        new Thread(()->{for (int i = 0; i < 60; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 0; i < 60; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 0; i < 60; i++) ticket.sale();},"C").start();
    }


}

/**
 * Lock 三部曲
 * 1. new ReentranLock;
 * 2.lock.lock(); 加锁
 * 3.finally=> lock.unlock(); 释放锁；
 */

class Ticket02{
    private int number=50;
    //synchronized 本质： 队列，锁
    Lock lock=new ReentrantLock();
    public  void  sale(){
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(50-number+1)+"张票");
                number--;
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            lock.unlock();
        }

    }
    //锁，锁对象，锁class
}
