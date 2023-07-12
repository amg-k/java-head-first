import java.util.*;

public class MountainCompare {
    public static void main(String[] args) {
        new MountainCompare().doIt();
    }

    public void doIt() {
        List<Mountain> mountains = new ArrayList<>();
        mountains.add(new Mountain("Kasprowy", 1987));
        mountains.add(new Mountain("Koscielec", 2155));
        mountains.add(new Mountain("Swinica", 2301));
        mountains.add(new Mountain("Rysy", 2499));
        System.out.println("Bez sortowania:\n" + mountains);

        mountains.sort((frst, scnd) -> frst.toString().compareTo(scnd.toString()));
        System.out.println("Wg nazw:\n" + mountains);

        mountains.sort((frst, scnd) -> scnd.height - frst.height);
        System.out.println("Wg wysokosci:\n" + mountains);
    }
}

class Mountain {
    final String name;
    final int height;

    Mountain(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public String toString() {
        return name + " " + height;
    }
}
