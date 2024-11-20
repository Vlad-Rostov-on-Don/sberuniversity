package homeworke.lesson2b;

public class Main {
    public static void main(String[] args) {

        Phone phone = new Phone();
        phone.addContacts("Ivanov", "89185552233");
        phone.addContacts("Petrov", "89897774411");
        phone.addContacts("Sidorov", "89035558899");
        phone.addContacts("Ivanov", "89005557789");

        phone.getContactsByLastname("Ivanov");

    }
}
