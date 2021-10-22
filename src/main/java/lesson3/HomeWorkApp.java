package lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeWorkApp {
    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();

        names.add("Вася");
        names.add("Петя");
        names.add("Саша");
        names.add("Вася");
        names.add("Вася");
        names.add("Петя");
        names.add("Даша");
        names.add("Маша");
        names.add("Даша");
        names.add("Глаша");


        HashMap<String, Integer> namesUniqueCount = new HashMap<>();

        for (String name: names) {
            if (namesUniqueCount.containsKey(name)) {
                int count = namesUniqueCount.get(name);
                count += 1;
                namesUniqueCount.put(name, count);
            } else {
                namesUniqueCount.put(name, 1);
            }
        }

        // Cписок уникальных слов
        System.out.println(namesUniqueCount.keySet());

        // [Маша, Вася, Глаша, Саша, Петя, Даша]

        // Подсчет слов
        System.out.println(namesUniqueCount);
        //{Маша=1, Вася=3, Глаша=1, Саша=1, Петя=2, Даша=2}



    }
}
