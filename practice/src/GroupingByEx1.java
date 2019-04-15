import java.util.Arrays;
import java.util.List;
import java.util.Map;
// import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingByEx1 {
public static void main(String[] args) {
    //3 apple, 2 banana, others 1
    List<String> items =  Arrays.asList
	("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

    Map<String, Long> result = items.stream()
	.collect( Collectors.groupingBy(Function.identity(), Collectors.counting()));

    // {papaya=1, orange=1, banana=2, apple=3}

    /* result 
     * < key     :  value >
     *   papaya  :  1
     *   orange  :  1
     *   banana  :  2
     *   apple   :  3
     */

    System.out.println(result);

    for(String s : result.keySet())  // s is an element of Set
	    System.out.println(s);

    for(Long v : result.values())   // values() :: Collection<V> = List<Long>
	    System.out.println(v);
}}
