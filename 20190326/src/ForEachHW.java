
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ForEachHW {
    public static void main(String args[]) {
        List<Integer> list1 = Arrays.asList(4, 7, 2, 8, 9, 3, 6); // list1 is fixed.
        ArrayList<Integer> evens = new ArrayList<Integer>(); // list2 is resizable.
        Integer max = 0;
        for (Integer e : list1) {
            if (e > max)
                max = e;
            if (e % 2 == 0)
                evens.add(e);
        }
        System.out.println(max);
        System.out.println(evens);
        evens.clear();
        list1.forEach (e -> { // lambda expression
           if(e%2==0)evens.add(e);
// if (e > max)
// max = e; // No assignment. Why?
            //람다 식을 사용할 경우 변수들이 final로 인식하기 때문에 값을 변경할 경우 에러가 난다.
        });
        System.out.println(evens);
    }
}