package homeworke.lesson2b;

import java.util.ArrayList;
import java.util.List;

public class Phone {
    private List<Contact> contacts = new ArrayList<>();

    //метод добавления контакта
    public void addContacts(String lastname, String phoneNumber) {
        contacts.add(new Contact(lastname, phoneNumber));
    }

    //метод поиска номера телефона по фамилии

    public void getContactsByLastname(String lastname){
        for (Contact contact : contacts) {
            if (contact.getLastname().equals(lastname)) {
                System.out.println(contact);
            }
        }
    }
}
