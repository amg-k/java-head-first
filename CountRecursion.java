import java.util.*;

public class CountRecursion {
    public static int numOfItems;
    public static int count(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        } else {
            list.remove(0);
            numOfItems++;
            CountRecursion.count(list);
        }
        return numOfItems;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(2, 4, 12, 7, 5, 
                                                        1, 1, 1, 1, 1));
        System.out.println(CountRecursion.count(list));
    }
}
