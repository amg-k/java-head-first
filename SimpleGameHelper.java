import java.util.Scanner;

public class SimpleGameHelper {
    public int inputData(String commentPhrase) {
        System.out.print(commentPhrase + ": ");
        Scanner skaner = new Scanner(System.in);
        return skaner.nextInt();
    }   
}