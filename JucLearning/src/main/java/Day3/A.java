package Day3;

/*
线程之间的通信问题，生产者和消费者问题； 等待唤醒，通知唤醒
线程交替执行 A B 操作同一个变量 num=0
A num+1
B num-1
 */
public class A {
    public static void main(String[] args) {
        Data data=new Data();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.add();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.dec();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"B").start();
    }


}

//判断等待，业务，通知
class Data {
    private int num = 0;

    //+1
    public synchronized void add() throws InterruptedException {
       while (num != 0) {
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        this.notifyAll();
    }

    //-1
    public synchronized void dec() throws InterruptedException {
        while (num == 0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        this.notifyAll();
    }
}
