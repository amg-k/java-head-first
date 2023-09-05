import java.util.*;

public class QuickSort {
    public static List<Integer> quickSort(List<Integer> list) {
        if (list.size() <= 1) {
            return list;
        } else {
            int pivot = list.remove(list.size()/2);
            List<Integer> less = new ArrayList<>();
            List<Integer> greater = new ArrayList<>();
            for (int elem : list) {
                if (elem <= pivot) {less.add(elem);}
                else {greater.add(elem);} 
            }
            List<Integer> sortedList = new ArrayList<>();
            sortedList.addAll(quickSort(less));
            sortedList.add(pivot);
            sortedList.addAll(quickSort(greater));
            return sortedList;
        }
    }

    public static void main(String[] args) {
        List<Integer> listToSort = new ArrayList<>(List.of(3, 1, 4, 6, 7,
                                                             9, 2, 5, 15, 17));
        long start = System.nanoTime();        
        List<Integer> sorted = QuickSort.quickSort(listToSort);
        long end = System.nanoTime();

        System.out.println("Executed in time " + (end-start));
        System.out.println(sorted);
    }
}
