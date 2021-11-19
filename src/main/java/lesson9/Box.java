package lesson9;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public float getBoxWeight() {
        if (this.fruits.size() == 0) return 0.0f;
        float itemWeight = fruits.get(0).getWeight();
        return fruits.size() * itemWeight;
    }

    public boolean compare(Box<? extends Fruit> anotherBox) {
        return this.getBoxWeight() == anotherBox.getBoxWeight();
    }

    public int getSize() {
        return this.fruits.size();
    }

    public void transfer(Box<T> targetBox) {
        targetBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
