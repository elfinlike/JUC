package Day6.future;

import Day3.C;

import java.util.concurrent.CompletableFuture;

public class Demo2 {
    /**
     * 有返回值的异步回调
     */
    public static void main(String[] args) throws Exception {

        //有返回值就异步调用，就会有两种回调结果
        //失败或者成功，成功就是返回正常的结果
        //失败返回的是错误信息；
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            System.out.println("子线程执行完毕");
            return "hello";
        });

        System.out.println("123456");
        // t 是正常执行的结果；
        // u 是错误信息；
        System.out.println(future.whenComplete((t, u) -> {
            System.out.println("结果是：" + t);
            System.out.println("错误信息是：" + u);
        }).exceptionally(e -> {
            System.out.println("异常是：" + e.getMessage());
            return "error";
        }).get());
    }
}
