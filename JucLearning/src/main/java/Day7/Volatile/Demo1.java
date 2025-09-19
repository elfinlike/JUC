package Day7.Volatile;

import java.util.concurrent.TimeUnit;

public class Demo1 {

    // 不加volatile 程序就会死循环；
    private static volatile int num = 0;
    public static void main(String[] args) {

        new Thread(()->{
            while (num == 0){
                // 循环检查num的值，如果num的值为0，则继续循环
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            num = 2;
            System.out.println("num="+num);
        }).start();
        new Thread(()->{
            num = 1;
            System.out.println("num="+num);
            while (num == 1){

            }
        }).start();
    }
}
