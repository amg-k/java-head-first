public class MaxValueRecursion {
    public static int findMaxValue(int[] list, int n) {
        if (n > 0) {
            return Math.max(list[n], findMaxValue(list, n-1));
        } else {
            return list[0];
        }
    }

    public static void main(String[] args) {
        int[] list = {3, 1, 4, 6, 7, 2, 5, 15, 17};

        System.out.println(findMaxValue(list, list.length-1));
    }
}
