import java.util.*;

public class GameHelperTester {
    public static void main(String[] args) {
        GameHelper testGameHelper = new GameHelper();
        ArrayList<String> testCoordinates = new ArrayList<String>();
        testCoordinates = testGameHelper.placeShip(3);
        
        /*
        testCoordinates.add("g2");
        testCoordinates.add("g3");
        testCoordinates.add("g4");
        */
        
        System.out.println(testCoordRange(testCoordinates));
    }

    private static boolean testCoordRange(ArrayList<String> testCoordinates) {
        String numRange = "0123456";
        String letterRange = "abcdefg";
        boolean isCorrect = true;

        for (String coord : testCoordinates) {
            if (!letterRange.contains(coord.subSequence(0, 1)) || 
            !numRange.contains(coord.subSequence(1, 2))) {
                isCorrect = false;
                break;
            }
        }
        System.out.println(testCoordinates);
        return isCorrect;
    }
}
