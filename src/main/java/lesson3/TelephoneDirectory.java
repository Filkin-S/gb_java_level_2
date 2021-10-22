package lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class TelephoneDirectory {
    private final HashMap<String, ArrayList<String>> directory;

    public TelephoneDirectory() {
        this.directory = new HashMap<>();
    }

    public void addPhoneNumber(String surname, String phoneNumber) {
        if (this.directory.containsKey(surname)) {
            ArrayList<String> numbers = this.directory.get(surname);
            numbers.add(phoneNumber);
        } else {
            ArrayList<String> numbers = new ArrayList<>();
            numbers.add(phoneNumber);
            this.directory.put(surname, numbers);
        }
    }

    public ArrayList<String> getPhoneNumber(String surname) {
        return this.directory.get(surname);
    }

}
