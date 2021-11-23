package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeWorkApp {

    /**
     * Задание 1
     */
    public static <T> void swapTwoArrayElements(List<T> array, int firstIndex, int secondIndex) {
        T firstElement = array.get(firstIndex);
        T secondElement = array.get(secondIndex);
        array.set(firstIndex, secondElement);
        array.set(secondIndex, firstElement);
    }

    /**
     * Задание 2
     */
    public static <T> ArrayList<T> arrayToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static void main(String[] args) {

        /**
         * Задание 1
         */
        List<String> strings = new ArrayList<>(Arrays.asList("1", "2", "3")) ;
        swapTwoArrayElements(strings, 1, 2);
        System.out.println(strings);
        // [1, 3, 2]

        /**
         * Задание 2
         */
        String[] users = {"John", "Jack", "Bob"};
        System.out.println(arrayToArrayList(users).getClass());
        // class java.util.ArrayList

        /**
         * Задание 3
         */
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Apple a3 = new Apple();
        Orange o1 = new Orange();
        Orange o2 = new Orange();

        Box<Apple> appleBox = new Box<>();
        appleBox.add(a1);
        appleBox.add(a2);
        appleBox.add(a3);
        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(o1);
        orangeBox.add(o2);

        System.out.println(appleBox.compare(orangeBox));
        // true

        Box<Apple> appleBox2 = new Box<>();
        Apple a4 = new Apple();
        appleBox2.add(a4);

        System.out.println(appleBox.getSize());
        System.out.println(appleBox2.getSize());
        //3
        //1

        appleBox.transfer(appleBox2);
        System.out.println(appleBox.getSize());
        System.out.println(appleBox2.getSize());
        //0
        //4
    }

}
