public class ObjectLifeTester {
    public static void main(String[] args) {
        Duck d = new Duck();
        System.out.println(d.name + " " + d.size);
        d = null;
        d = new Duck();
        System.out.println("New duck: " + d.name + " " + d.size);

        int[] tBase = {2, 42, 12, 21, 9, 3};
        int[] tTest;
        tTest = tBase;
        for (int elem : tTest) {
            System.out.print(elem + " ");
        }
        
        System.out.println();

        for (int elem : tBase) {
            System.out.print(elem + " ");
        }
    }
}

class Duck {
    int size;
    String name;

    public Duck() {
        size = (int) (Math.random() * 7);
        name = "Donald";
    }
}