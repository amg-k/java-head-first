class MyEx extends Exception {}

public class ExTester {
    public static void main(String[] args) {
        String test = args[0];
        try {
            System.out.print("p");
            takeRisk(test);
        } catch (MyEx e) {
            System.out.print("a");
        } finally {
            System.out.print("n");
            System.out.println("a");
        }
    }

    static void takeRisk(String t) throws MyEx {
        System.out.print("i");
        if ("yes".equals(t)) {
            throw new MyEx();
        }
        System.out.print("j");
        System.out.print("a");
    }
}
