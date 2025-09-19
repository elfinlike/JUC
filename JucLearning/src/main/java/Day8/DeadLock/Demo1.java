package Day8.DeadLock;

public class Demo1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("lockA", "lockB");
        MyThread t2 = new MyThread("lockB", "lockA");
        t1.start();
        t2.start();
    }

}

class MyThread extends Thread {

    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + lockA+"==>"+lockB);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + lockB+"==>"+lockA);
            }
        }
    }
}
