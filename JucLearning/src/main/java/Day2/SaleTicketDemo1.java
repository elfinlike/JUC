package Day2;

/**
 * 真正的多线程开发，降低耦合性
 * 线程是一个单独的资源类，没有任何附属的操作；
 * 1.属性，方法
 */
public class SaleTicketDemo1 {
    public static void main(String[] args) {

        //并发：多线程操作同一个资源类；
        Ticket ticket=new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++) {
                    ticket.sale();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++) {
                    ticket.sale();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++) {
                    ticket.sale();
                }
            }
        },"C").start();
    }


}
class Ticket{
    private int number=50;
    //synchronized 本质： 队列，锁
    public synchronized void  sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(50-number+1)+"张票");
            number--;
        }
    }
    //锁，锁对象，锁class
}