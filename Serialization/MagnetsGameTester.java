package Serialization;

import java.io.*;

class MagnetsGameTester {
    public static void main(String[] args) {
        MagnetsGame g = new MagnetsGame();
        System.out.println(g.getX() + g.getY() + g.getZ());

        try {
            FileOutputStream fos = new FileOutputStream("game.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(g);
            oos.close();

            FileInputStream fis = new FileInputStream("game.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            g = (MagnetsGame) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(g.getX() + g.getY() + g.getZ());
    }        
}

class MagnetsGame implements Serializable {
    public int x = 3;
    transient long y = 4;
    private short z = 5;

    int getX() {
        return x;
    }

    long getY() {
        return y;
    }

    short getZ() {
        return z;
    }
}
