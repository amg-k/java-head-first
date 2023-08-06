package Serialization;

import java.io.*;

public class GameSaver {
    public static void main(String[] args) {
        Hero char1 = new Hero(50, "Elf", new String[] {"bow", "dagger", "potion"});
        Hero char2 = new Hero(65, "Orc", new String[] {"mace", "axe", "stone"});
        Hero char3 = new Hero(32, "Human", new String[] {"staff", "book", "scroll"});
    
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("game.ser"));
            os.writeObject(char1);
            os.writeObject(char2);
            os.writeObject(char3);
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("game.ser"));
            Hero restoreChar1 = (Hero) is.readObject();
            Hero restoreChar2 = (Hero) is.readObject();
            Hero restoreChar3 = (Hero) is.readObject();
            is.close();

            System.out.println("Character 1 weapon: " + restoreChar1.getWeapon());
            System.out.println("Character 2 weapon: " + restoreChar2.getWeapon());
            System.out.println("Character 3 weapon: " + restoreChar3.getWeapon());

            if (char1.getRace().equals(restoreChar1.getRace())) {
                System.out.println("Game restored successfully!");
            } else {
                System.out.println("Game restored has failed!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
