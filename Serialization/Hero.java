package Serialization;

import java.io.*;
import java.util.Arrays;


public class Hero implements Serializable{
    private final int power;
    private final String race;
    private final String[] weapon;

    public Hero(int power, String race, String[] weapon) {
        this.power = power;
        this.race = race;
        this.weapon = weapon;
    }

    public int getPower() {
        return power;
    }
    
    public String getRace() {
        return race;
    }
    
    public String getWeapon() {
        return Arrays.toString(weapon);
    }
}
