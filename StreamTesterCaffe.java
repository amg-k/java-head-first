import java.util.*;
import java.util.stream.*;

public class StreamTesterCaffe {
    public static void main(String[] args) {
        List<String> coffees = List.of("Cappuccino", "Americano", "Espresso", "Cortado", "Mocha", 
            "Cappuccino", "Flat White", "Latte");
        List<String> coffeesEndsO = coffees.stream().distinct().sorted().filter(s -> s.endsWith("o"))
            .collect(Collectors.toList());

        System.out.println(coffeesEndsO);
    }
}
