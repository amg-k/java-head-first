import java.util.*;

public class TreeSetTest {
    public static void main(String[] args) {
        new TreeSetTest().doIt();
    }

public void doIt() {
    TreeBook bk1 = new TreeBook("The Lord of the Rings");
    TreeBook bk2 = new TreeBook("War and Peace");
    TreeBook bk3 = new TreeBook("Pan Tadeusz");

    Set<TreeBook> bookSet = new TreeSet<>();
    bookSet.add(bk1);
    bookSet.add(bk2);
    bookSet.add(bk3);
    System.out.println(bookSet);
}
}

class TreeBook implements Comparable<TreeBook> {
    private String title;

    public TreeBook(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

    public int compareTo(TreeBook b) {
        return this.getTitle().compareTo(b.getTitle());
    }

    public String toString() {
        return title;
    }
}