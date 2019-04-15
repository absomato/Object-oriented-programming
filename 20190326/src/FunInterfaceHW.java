import com.sun.org.apache.xpath.internal.operations.Bool;

@FunctionalInterface
interface Square<T,R>{
    Boolean calculate(Character x);
}
public class FunInterfaceHW {
    public static void main(String args[]){
        Square<Character,Boolean> s = x -> x=='A' ? true:false;
        Boolean ans = s.calculate('A');
        System.out.println(ans);
    }
}