#### 1.ForkJoin:

ForkJoin 在JDK1.7，并行执行任务！提高效率~。在大数据量速率会更快！

大数据中：**MapReduce 核心思想->把大任务拆分为小任务！**

<img src="https://i-blog.csdnimg.cn/blog_migrate/db9aed609e9af529d5ee9329d1abb042.png" alt="image-20200812163638389" style="zoom: 80%;" />

#### 1）ForkJoin 特点： 工作窃取！

实现原理是：**双端队列**！从上面和下面都可以去拿到任务进行执行！

![image-20200812163701588](https://i-blog.csdnimg.cn/blog_migrate/7a598d51e33ab37e96a51cac93252dd7.png)

#### 2）如何使用ForkJoin?

- 1、通过**ForkJoinPool**来执行

- 2、计算任务 **execute(ForkJoinTask<?> task)**

- 3、计算类要去继承ForkJoinTask；

  **ForkJoin 的计算类**

  ```
  package com.marchsoft.forkjoin;
  
  import java.util.concurrent.RecursiveTask;
  
  
  public class ForkJoinDemo extends RecursiveTask<Long> {
      private long star;
      private long end;
      /** 临界值 */
      private long temp = 1000000L;
  
      public ForkJoinDemo(long star, long end) {
          this.star = star;
          this.end = end;
      }
  
      /**
       * 计算方法
       * @return
       */
      @Override
      protected Long compute() {
          if ((end - star) < temp) {
              Long sum = 0L;
              for (Long i = star; i < end; i++) {
                  sum += i;
              }
              return sum;
          }else {
              // 使用ForkJoin 分而治之 计算
              //1 . 计算平均值
              long middle = (star + end) / 2;
              ForkJoinDemo forkJoinDemo1 = new ForkJoinDemo(star, middle);
              // 拆分任务，把线程压入线程队列
              forkJoinDemo1.fork();
              ForkJoinDemo forkJoinDemo2 = new ForkJoinDemo(middle, end);
              forkJoinDemo2.fork();
  
              long taskSum = forkJoinDemo1.join() + forkJoinDemo2.join();
              return taskSum;
          }
      }
  }
  
  ```

- 测试类

  ```
  package com.marchsoft.forkjoin;
  
  import java.util.concurrent.ExecutionException;
  import java.util.concurrent.ForkJoinPool;
  import java.util.concurrent.ForkJoinTask;
  import java.util.stream.LongStream;
  
  
  public class ForkJoinTest {
      private static final long SUM = 20_0000_0000;
  
      public static void main(String[] args) throws ExecutionException, InterruptedException {
          test1();
          test2();
          test3();
      }
  
      /**
       * 使用普通方法
       */
      public static void test1() {
          long star = System.currentTimeMillis();
          long sum = 0L;
          for (long i = 1; i < SUM ; i++) {
              sum += i;
          }
          long end = System.currentTimeMillis();
          System.out.println(sum);
          System.out.println("时间：" + (end - star));
          System.out.println("----------------------");
      }
      /**
       * 使用ForkJoin 方法
       */
      public static void test2() throws ExecutionException, InterruptedException {
          long star = System.currentTimeMillis();
  
          ForkJoinPool forkJoinPool = new ForkJoinPool();
          ForkJoinTask<Long> task = new ForkJoinDemo(0L, SUM);
          ForkJoinTask<Long> submit = forkJoinPool.submit(task);
          Long along = submit.get();
  
          System.out.println(along);
          long end = System.currentTimeMillis();
          System.out.println("时间：" + (end - star));
          System.out.println("-----------");
      }
      /**
       * 使用 Stream 流计算
       */
      public static void test3() {
          long star = System.currentTimeMillis();
  
          long sum = LongStream.range(0L, 20_0000_0000L).parallel().reduce(0, Long::sum);
          System.out.println(sum);
          long end = System.currentTimeMillis();
          System.out.println("时间：" + (end - star));
          System.out.println("-----------");
      }
  }
  
  ```

  

  // TODO: 	异步回调；

  