package lock8;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 8锁：
 * 1.标准访问，两个线程先打印 发送短信还是 发送邮件
 * 2.sendEmail 延迟1秒，先打印 发送邮件还是 发送短信
 * 3.两个线程同时访问一个静态同步方法，先打印 发送短信还是 发送邮件
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

class Phone {

    //synchronized 锁的对象是方法的调用者
    //两个方法使用的是同一把锁，谁先拿到就谁先执行
    public synchronized void sendSMS() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("sendEmail");
    }
}
