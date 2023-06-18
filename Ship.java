import java.util.ArrayList;

public class Ship {
    private ArrayList<String> positionCells;
    private String name;

    public void setPositionCells(ArrayList<String> posCells) {
        positionCells = posCells;
    }

    public void setName(String n) {
        name = n;
    }

    public String check(String playerAim) {
        String result = "Miss";
        int index = positionCells.indexOf(playerAim);
        if (index >= 0) {
            positionCells.remove(index);

            if (positionCells.isEmpty()) {
                result = "Hit-Sunk!";
                System.out.println("You've sunk down the ship " + name + "!");
            } else {
                result = "Hit!";
            }
        }
        return result;
    }
}
