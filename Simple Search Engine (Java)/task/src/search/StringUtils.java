package search;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class StringUtils {
    public static int find(String searchWord, String sentence) {
        AtomicInteger position = new AtomicInteger(-1);
        var foundString = Arrays.stream(sentence.split(" "))
                .peek(s -> position.addAndGet(1))
                .filter(s -> s.equals(searchWord))
                .findFirst();
        if (foundString.isPresent()) {
            return position.get();
        }
        return -1;
    }
}
