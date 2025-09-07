package Day8.CompetebaleLock;

public class Demo1 {
    public static void main(String[] args) {

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
        public synchronized void sendSMS() throws Exception{
            System.out.println(Thread.currentThread().getName() +"sendSMS");
            sendEmail();
        }
        public  void sendEmail() throws Exception{
            System.out.println(Thread.currentThread().getName()+"sendEmail");
        }
    }
}
