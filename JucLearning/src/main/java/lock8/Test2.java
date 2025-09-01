package lock8;

import java.util.concurrent.TimeUnit;

/*
8锁：
1.标准访问，两个线程先打印短信还是邮件？ 短信
2.sendEmail和show先执行哪一个？ show
 */
// 两个对象的Class类模板只有一个，static的synchronized方法锁的是class
public class Test2 {
    public static void main(String[] args) throws Exception{
        Phone2 phone = new Phone2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone.sendSMS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class Phone2 {
    public  synchronized void sendSMS() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendSMS");
    }
    public  synchronized void sendEmail() throws Exception{
        System.out.println("sendEmail");
    }
    // 没有锁，不受锁的影响
    public void show(){
        System.out.println("show");
    }
}
