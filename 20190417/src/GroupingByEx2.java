import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Item {
    private String name;
    private int qty;
    private BigDecimal price;

    //constructors, getter/setters
    Item(String name, int qty, BigDecimal price) {
	this.name = name; this.qty = qty; this.price = price;
    }
    
    String getName() { return this.name; }
    int getQty() { return this.qty; }
    // BigDecimal get() { return this.name; }
}

public class GroupingByEx2 {
public static void main(String[] args) {

    //3 apple, 2 banana, others 1
    List<Item> items = Arrays.asList(
        new Item("apple", 10, new BigDecimal("9.99")),
        new Item("banana", 20, new BigDecimal("19.99")),
        new Item("orang", 10, new BigDecimal("29.99")),
        new Item("watermelon", 10, new BigDecimal("29.99")),
        new Item("papaya", 20, new BigDecimal("9.99")),
        new Item("apple", 10, new BigDecimal("9.99")),
        new Item("banana", 10, new BigDecimal("19.99")),
        new Item("apple", 20, new BigDecimal("9.99"))
    );

    Map<String, Long> counting = items.stream().collect(
            Collectors.groupingBy(Item::getName, Collectors.counting()));

    System.out.println(counting);

    Map<String, Integer> sum = items.stream().collect(
            Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

    System.out.println(sum);
}}
