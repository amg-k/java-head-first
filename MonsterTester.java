public class MonsterTester {
    public static void main(String[] args) {
        Monster[] monsters = new Monster[3];
        monsters[0] = new Vampire();
        monsters[1] = new Dragon();
        monsters[2] = new Monster();
        for (int i = 0; i < monsters.length; i++) {
            monsters[i].fear(i);
        }
    }
}

class Monster {
    boolean fear(int d) {
        System.out.println("auułuu auu");
        return true;
    }
}

class Vampire extends Monster {
    boolean fear(int d) {
        System.out.println("a small bite?");
        return false;
    } 
}

class Dragon extends Monster {
    boolean fear(int d) {
        System.out.println("fire blast");
        return true;
    }
}