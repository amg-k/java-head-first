import java.util.*;

public class SumRecurrency {
    public static int sum(List<Integer> list) {
        return (list.isEmpty()) ? 0 : list.remove(0) + sum(list);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(2, 4, 12, 7, 5));
        System.out.println(SumRecurrency.sum(list));
    }
}
