package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeWorkApp {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>(Arrays.asList("1", "2", "3")) ;
        swapTwoArrayElements(strings, 1, 2);
        System.out.println(strings);

        String[] users = {"John", "Jack", "Bob"};
        System.out.println(arrayToArrayList(users).getClass());

        Apple a1 = new Apple();
        Apple a2 = new Apple();

        Box<Apple> appleBox = new Box<>();
        appleBox.add(a1);
        appleBox.add(a2);

    }

    public static <T> void swapTwoArrayElements(List<T> array, int firstIndex, int secondIndex) {
        T firstElement = array.get(firstIndex);
        T secondElement = array.get(secondIndex);
        array.set(firstIndex, secondElement);
        array.set(secondIndex, firstElement);
    }

    public static <T> ArrayList<T> arrayToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
