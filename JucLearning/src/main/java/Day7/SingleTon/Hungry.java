package Day7.SingleTon;

import java.lang.reflect.Constructor;

//饿汉式单例
public class Hungry {

    private Hungry()
    {

    }

    //可能会浪费空间
    private static final Hungry instance = new Hungry();

    public static Hungry getInstance()
    {
        return instance;
    }

    //使用反射来破坏这种单例模式
    public static void main(String[] args) throws Exception {
        Hungry instance1 = Hungry.getInstance();
        Constructor<Hungry> constructor = Hungry.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Hungry instance2 = constructor.newInstance();
        System.out.println(instance1 == instance2);
    }
}
