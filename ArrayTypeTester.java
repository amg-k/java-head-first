import java.util.ArrayList;

public class ArrayTypeTester {
    public static void main(String[] args) {
        ArrayList<Object> animalList = new ArrayList<Object>();
        Cat c = new Cat();
        Dog d = new Dog();
        animalList.add(c);
        animalList.add(d);

        Cat aniCat = (Cat) animalList.get(0);
        aniCat.setWeight(7);
        System.out.println(aniCat.weight);
        aniCat.voice();
    }
}

class Animal {
    int weight;

    public void setWeight(int w) {
        if (w > 0) {
            weight = w;
        } else {
            System.out.println("Wrong value");
        }
    }

    public void voice() {
        System.out.println("-general-animal-voice-");
    }
}

class Cat extends Animal {
    public void voice() {
        System.out.println("Miauu");
    }
}

class Dog extends Animal {
    public void voice() {
        System.out.println("Hauu");
    }
}
