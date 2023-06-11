public class SimpleShipGame {
    public static void main(String[] args) {
        int moveCount = 0;
        GameHelper helper = new GameHelper();
        SimpleShip gameSimpleShip = new SimpleShip();
        int crdStart = (int) (Math.random() * 5);
        int[] inCrdShipList = {crdStart, crdStart + 1, crdStart + 2};
        gameSimpleShip.setCoordinates(inCrdShipList);
        boolean gameState = true;

        while (gameState) {
            int playerAim = helper.inputData("Type number: ");
            String turnResult = gameSimpleShip.hitAssessment(playerAim);
            moveCount++;
            if (turnResult.equals("Sinked!")) {
                gameState = false;
                System.out.println("Number of moves: " + moveCount);
            }
        }
    }
}