package Day5;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer=(str)->{
            System.out.println(str);
        };
        consumer.accept("hello world");
    }
}
