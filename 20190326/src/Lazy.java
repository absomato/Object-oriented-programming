import java.util.List;
import java.util.Arrays;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

public class Lazy {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares = numbers.stream()
                .filter(n -> {
                    System.out.println("filtering " + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("mapping " + n);
                    return n * n;
                })
                .limit(2)
                .collect(toList());
        System.out.println(twoEvenSquares);
    }
}
//map까지 짝수의 수들은 넘어가지만 밑에 limit(2)2개까지만 보내라고 했으니 twoEvenSquares에는 4와 16이 저장된다.