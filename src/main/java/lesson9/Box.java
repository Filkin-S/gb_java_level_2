package lesson9;

import java.util.ArrayList;

public class Box <T extends Fruit> {
    ArrayList<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }


}
