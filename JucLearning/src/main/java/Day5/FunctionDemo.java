package Day5;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<String,String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };
        System.out.println(function.apply("hello"));
    }
}
