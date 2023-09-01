import java.util.*;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        int[] myList = {12, 3, 123, 23, 11, 453, 21, 54, 67, 32, 88, 21, 4, 6, 7, 71};
        Arrays.sort(myList);
        System.out.println(Arrays.toString(myList));
        int result = search.binarySearch(myList, 3);
        System.out.println(result);
    }

    public int binarySearch(int[] list, int elem) {
        int low = 0;
        int high = list.length -1;
        int mid, guess;

        while (low <= high) {
            mid = (low + high)/2;
            guess = list[mid];
            if (guess == elem) {
                return mid;
            } else if (guess > elem) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
