package Day5;

import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("hello");
            }
        };

        System.out.println(predicate.test("hello"));
    }
}
