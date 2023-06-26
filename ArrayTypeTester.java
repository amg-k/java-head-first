import java.util.ArrayList;

public class ArrayTypeTester {
    public static void main(String[] args) {
        ArrayList<Animal> animalList = new ArrayList<Animal>();
        Cat c = new Cat();
        Dog d = new Dog();
        animalList.add(c);
        animalList.add(d);

        //animalList[1].catVoice();
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
}

class Cat extends Animal {
    public void catVoice() {
        System.out.println("Miauu");
    }
}

class Dog extends Animal {
    public void dogVoice() {
        System.out.println("Hauu");
    }
}
