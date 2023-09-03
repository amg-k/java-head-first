import java.util.*;

public class SelectionSort {
    public int[] selectionSort(int[] arr) {
        int[] sortedArr = new int[arr.length];
        List<Integer> tmpList = new ArrayList<>();
        for (int elem : arr) {
            tmpList.add(elem);
        }
        
        int listSize = tmpList.size();

        for (int i = 0; i < listSize; i++) {
            sortedArr[i] = tmpList.remove(findMinElemIndex(tmpList));
        }
        return sortedArr;
    }

/*     public String[] selectionSort(String[] arr) {
        String[] sortedArr = new String[arr.length];
        List<String> tmpList = new ArrayList<>();
        for (String elem : arr) {
            tmpList.add(elem);
        }
        
        int listSize = tmpList.size();

        for (int i = 0; i < listSize; i++) {
            sortedArr[i] = tmpList.remove(findMinElemIndex(tmpList));
        }
        return sortedArr;
    }
 */
/*     public int findMinElemIndex(int[] arr) {
        int tmp = arr[0];
        int minIndex = 0;
        int endIndex = arr.length - 1;
        for (int i = 1; i <= endIndex; i++) {
            if (arr[i] < tmp) {
                tmp = arr[i];
                minIndex = i;
            } 
        }
        return minIndex;
    } */

    public int findMinElemIndex(List<Integer> list) {
        int tmp = list.get(0);
        int minIndex = 0;
        int endIndex = list.size() - 1;
        for (int i = 1; i <= endIndex; i++) {
            if (list.get(i) < tmp) {
                tmp = list.get(i);
                minIndex = i;
            } 
        }
        return minIndex;
    }

/*     public int findMinElemIndex(List<String> list) {
        String tmp = list.get(0);
        int minIndex = 0;
        int endIndex = list.size() - 1;
        for (int i = 1; i <= endIndex; i++) {
            if (list.get(i).compareToIgnoreCase(tmp) < 0) {
                tmp = list.get(i);
                minIndex = i;
            } 
        }
        return minIndex;
    } */

    public static void main(String[] args) {
        SelectionSort select = new SelectionSort();
        int[] intArr = {12, 11, 1, 43, 22, 51, 75, 13, 77, 48, 9, 17};
        //String[] strArr = {"jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};

        System.out.println(Arrays.toString(select.selectionSort(intArr)));
    }
}
