package Day7.SingleTon;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class LazyMan1 {

    private static boolean flag = false;
    private LazyMan1()
    {
        System.out.println(Thread.currentThread().getName()+"创建对象");
        synchronized (LazyMan.class)
        {
            if (!flag){
                flag = true;
            }
            else
            {
                throw new RuntimeException("单例被侵犯");
            }
        }
    }

    //保证其不会被指令重排
    private volatile static LazyMan1 instance;


    // 双重检测锁模式的懒汉式单例 DCL懒汉式
    public static LazyMan1 getInstance()
    {
        if(instance == null)
        {
            synchronized (LazyMan1.class)
            {
                if(instance == null)
                {
                    instance = new LazyMan1();//该操作不是一个原子性操作；
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        Field cs=LazyMan1.class.getDeclaredField("flag");
        Constructor<LazyMan1> constructor = LazyMan1.class.getDeclaredConstructor(null);
        cs.setAccessible(true);
        constructor.setAccessible(true);
        LazyMan1 instance = constructor.newInstance();

        cs.set(instance,false);

        LazyMan1 instance1 = constructor.newInstance();

        System.out.println(instance == instance1);
    }
}
