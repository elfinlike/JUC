package Day7.SingleTon;

//静态内部类 实现
//可以实现，但是是不安全的，因为可以通过反射破坏单例
public class Holder {
    private Holder(){}

    public static Holder getInstance(){
        return InstanceHolder.instance;
    }
    private static class InstanceHolder{
        private static final Holder instance = new Holder();
    }
}
