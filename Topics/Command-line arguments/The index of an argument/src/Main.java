import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

class Problem {
    public static void main(String[] args) {
        AtomicInteger position = new AtomicInteger(-1);
        var foundString = Arrays.stream(args)
                .peek(s -> position.addAndGet(1))
                .filter("test"::equals)
                .findFirst();
        if (foundString.isPresent()) {
            System.out.print(position.get());
        } else {
            System.out.print(-1);
        }
    }
}