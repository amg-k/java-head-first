import java.util.ArrayList;

public class ShipGame {
    private GameHelper helper = new GameHelper();
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private int moveNumber = 0;
    
    private void prepareGame() {
        Ship firstShip = new Ship();
        firstShip.setName("Eagle");
        Ship secondShip = new Ship();
        secondShip.setName("Albion");
        Ship thirdShip = new Ship();
        thirdShip.setName("Bulwark");
        ships.add(firstShip);
        ships.add(secondShip);
        ships.add(thirdShip);

        System.out.println("Your goal is to sink down three ships from Suez conflict.");
        System.out.println("Eagle, Albion, Bulwark");
        System.out.println("Try to do it in as few moves as possible.");

        for (Ship ship : ships) {
            ArrayList<String> newPosition = helper.placeShip(3);
            ship.setPositionCells(newPosition);
        }
    }

    private void startGame() {
        while (!ships.isEmpty()) {
            String playerMove = helper.getInputData("Type field");
            checkPlayerMove(playerMove);
        }
        endGame();
    }

    private void checkPlayerMove(String playerMove) {
        moveNumber++;
        String result = "Miss";

        for (Ship shipToCheck : ships) {
            result = shipToCheck.check(playerMove);
            if (result.equals("Hit!")) {
                break;
            }
            if (result.equals("Hit-Sunk!")) {
                ships.remove(shipToCheck);
                break;
            }
        }
        System.out.println(result);
    }

    private void endGame() {
        System.out.println("All ships are sunk down! Our coastline is safe...");
        if (moveNumber <= 18) {
            System.out.println("You've used only " + moveNumber + " artillery shells...");
            System.out.println("Your battle skills are excellent. You are reported to award Merit Order!");
        } else {
            System.out.println("You've used much to many ammunition. " + moveNumber + " artillery shells it's almost our entire stock!");
            System.out.println("Your battle skills are really poor. We must consider your next assignment...");
        }
    }

    public static void main(String[] args) {
        ShipGame game = new ShipGame();
        game.prepareGame();
        game.startGame();
    }
}
