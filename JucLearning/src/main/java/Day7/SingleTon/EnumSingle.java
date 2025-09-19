package Day7.SingleTon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// enum 是一个是什么？ 本身也是一个Class类
public enum EnumSingle {
    INSTANCE;

    public EnumSingle getInstance()
    {
        return INSTANCE;
    }
}
class Test {
    // NoSuchMethodException 没有空参构造方法
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSingle instance = EnumSingle.INSTANCE;
        Constructor<EnumSingle> constructor = EnumSingle.class.getDeclaredConstructor(String.class,Integer.class);
        constructor.setAccessible(true);
        EnumSingle instance1 = constructor.newInstance();
        System.out.println(instance == instance1);
    }
}
