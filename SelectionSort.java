public class SelectionSort {
    public <T extends Comparable<Integer>> T[] selectionSort(T[] arr) {
        
        return arr;
    }

    public int findMinElemIndex(int[] arr) {
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
    }

    public static void main(String[] args) {
        SelectionSort select = new SelectionSort();
        int[] arr = {12, 11, 1, 43, 22, 51, 75, 13, 77, 48, 9, 17};
        
        int minIndex = select.findMinElemIndex(arr);
        System.out.println(minIndex);
    }
}
