import java.util.*;

public class ForLoopTrouble {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        String results = "";

/*         for (Integer number : numbers) {
            results += number + " ";
        } */


/*         for (int i = 0; i < numbers.size(); i++) {
            results += numbers.get(i) + " "; 
        } */

        for (Integer number : numbers) {
            results = results.concat(String.valueOf(number) + " ");
        }

        System.out.println(results);
    }
}
