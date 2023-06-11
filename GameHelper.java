import java.util.Scanner;

public class GameHelper {
    public int inputData(String commentPhrase) {
        System.out.print(commentPhrase + ": ");
        Scanner skaner = new Scanner(System.in);
        return skaner.nextInt();
    }   
}