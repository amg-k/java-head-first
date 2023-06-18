import java.util.*;

public class GameHelper {
    private static final String ALPHABET = "abcdefg";
    private static final int BOARD_LENGHT = 7;
    private static final int BOARD_SIZE = 49;   
    private static final int MAX_ATTEMPT = 200;
    static final int INCREMENTATION_HORIZONTAL = 1;
    static final int INCREMENTATION_UP = BOARD_LENGHT;

    private final int[] board = new int[BOARD_SIZE];
    private final Random randomNumber = new Random();
    private int shipsNumber = 0;

    public String getInputData(String statement) {
        System.out.print(statement + ": ");
        Scanner myScanner = new Scanner(System.in);
        return myScanner.nextLine().toLowerCase();
    }

    public ArrayList<String> placeShip(int shipSize) {
        int[] shipCoordinates = new int[shipSize];
        int attemptNum = 0;
        boolean isSuccess = false;

        shipsNumber++;
        int incrementationValue = getIncrementationValue();

        while (!isSuccess & attemptNum++ < MAX_ATTEMPT) {
            int position = randomNumber.nextInt(BOARD_SIZE);

            for (int i = 0; i < shipCoordinates.length; i++) {
                shipCoordinates[i] = position;
                position += incrementationValue;
            }
            //System.out.println("Try: " + Arrays.toString(shipCoordinates));

            if (isShipFits(shipCoordinates, incrementationValue)) {
                isSuccess = isCoordinatesAvaliable(shipCoordinates);
            }
        }
        savePlaceOnBoard(shipCoordinates);
        ArrayList<String> cellAlfaNum = convertPositionToAlfaNum(shipCoordinates);
        //System.out.println("Ship placed in field: " + cellAlfaNum);
        return cellAlfaNum;
    }

    private boolean isShipFits(int[] shipCoordinates, int incrementationValue) {
        int finalPosition = shipCoordinates[shipCoordinates.length - 1];
        if (incrementationValue == INCREMENTATION_HORIZONTAL) {
            return calculateRowFromIndex(shipCoordinates[0]) == calculateRowFromIndex(finalPosition);
        } else {
            return finalPosition < BOARD_SIZE;
        }
    }

    private boolean isCoordinatesAvaliable(int[] shipCoordinates) {
        for (int coordinate : shipCoordinates) {
            if (board[coordinate] != 0) {
                //System.out.println("Coordinate: " + coordinate + "are not avaliable.");
                return false;
            }
        }
        return true;
    }

    private void savePlaceOnBoard(int[] shipCoordinates) {
        for (int index : shipCoordinates) {
            board[index] = 1;
        }
    }

    private ArrayList<String> convertPositionToAlfaNum(int[] shipCoordinates) {
        ArrayList<String> cellAlfaNum = new ArrayList<String>();
        for (int index : shipCoordinates) {
            String coordinatesAlfaNum = getAlfaNumCoordByIndex(index);

            cellAlfaNum.add(coordinatesAlfaNum);
        }
        return cellAlfaNum;
    }

    private String getAlfaNumCoordByIndex(int index) {
        int row = calculateRowFromIndex(index);
        int column = index % BOARD_LENGHT;
        String letter = ALPHABET.substring(column, column + 1);
        return letter + row;
    }

    private int calculateRowFromIndex(int index) {
        return index / BOARD_LENGHT;
    }

    private int getIncrementationValue() {
        if (shipsNumber % 2 == 0) {
            return INCREMENTATION_HORIZONTAL;
        } else {
            return INCREMENTATION_UP;
        }
    }
}