class StaticSuper {
    static {
        System.out.println("Static initializer block");
    }

    StaticSuper() {
        System.out.println("Base constructor");
    }
}
public class StaticTester extends StaticSuper {
    static int rnd;
    
    {
    System.out.println("test");
    }
    
    static {
        rnd = (int) (Math.random() * 5);
        System.out.println("Static initializer block " + rnd);
    }

    StaticTester() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        System.out.println("Main method");
        StaticTester st = new StaticTester();
        StaticTester stNew = new StaticTester();
    }
}
