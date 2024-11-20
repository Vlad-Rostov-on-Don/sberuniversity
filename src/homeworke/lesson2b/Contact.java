package homeworke.lesson2b;

public class Contact {
    private String lastname;
    private String phoneNumber;

    public Contact(String lastname, String phoneNumber) {
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
