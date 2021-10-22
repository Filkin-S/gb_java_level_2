package lesson3;

public class TelephoneDirectoryApp {
    public static void main(String[] args) {

        TelephoneDirectory myTelephoneDirectory = new TelephoneDirectory();

        myTelephoneDirectory.addPhoneNumber("Филькин", "332114");
        myTelephoneDirectory.addPhoneNumber("Гусев", "457774");
        myTelephoneDirectory.addPhoneNumber("Филькин", "994220");

        System.out.println(myTelephoneDirectory.getPhoneNumber("Филькин"));
        System.out.println(myTelephoneDirectory.getPhoneNumber("Гусев"));
        System.out.println(myTelephoneDirectory.getPhoneNumber("Иванов"));

        /** вывод
         * [332114, 994220]
         * [457774]
         * null
         */


    }
}
