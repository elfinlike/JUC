package Day7.SingleTon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//懒汉式单例
public class LazyMan {
    private LazyMan()
    {
        System.out.println(Thread.currentThread().getName()+"创建对象");
        synchronized (LazyMan.class)
        {
            if(instance != null)
            {
                throw new RuntimeException("单例被侵犯");
            }
        }
    }

    //保证其不会被指令重排
    private volatile static LazyMan instance;


    // 双重检测锁模式的懒汉式单例 DCL懒汉式
    public static LazyMan getInstance()
    {
        if(instance == null)
        {
            synchronized (LazyMan.class)
            {
                if(instance == null)
                {
                    instance = new LazyMan();//该操作不是一个原子性操作；
                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个内存空间
                     *
                     * 123
                     * 根据指令重排后可能变成 132
                     * 变成132后就可能出现，其它线程在未初始化时就指向了该空间，就会导致一个null的错误
                     *
                     */
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> constructor = LazyMan.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        LazyMan instance1 = constructor.newInstance();
        System.out.println(instance == instance1);
    }
}
