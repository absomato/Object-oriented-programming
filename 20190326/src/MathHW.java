import java.util.function.BiFunction;
import java.util.function.Consumer;

public class MathHW {
    public static void main(String args[]) {


        MathHW tester = new MathHW();

        BiFunction<Integer,Integer,Integer> addition=(a,b)->a+b;
        BiFunction<Integer,Integer,Integer> subtraction=(a,b)->a-b;
        BiFunction<Integer,Integer,Integer> multipleication=(a,b)->a*b;
        BiFunction<Integer,Integer,Integer> division=(a,b)->a/b;

        System.out.println("10 + 5 = " + addition.apply(10,5));
        System.out.println("10 - 5 = " + subtraction.apply(10,5));
        System.out.println("10 * 5 = " + multipleication.apply(10,5));
        System.out.println("10 / 5 = " + division.apply(10,5));

        //without parenthesis
        Consumer<String> greetService1 = message ->
                System.out.println("Hello " + message);

        //with parenthesis
        Consumer<String> greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.accept("Mahesh");
        greetService2.accept("Suresh");
    }


}
