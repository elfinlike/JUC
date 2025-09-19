package Day6.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：  CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo1 {
    /**
     * 没有返回值的异步回调
     */
    public static void main(String[] args) {
        CompletableFuture<Void> future =CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println("子线程执行完毕");
        });
        System.out.println("111");
        try {
            future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}