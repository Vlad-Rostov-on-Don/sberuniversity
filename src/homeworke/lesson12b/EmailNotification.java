package homeworke.lesson12b;

public class EmailNotification extends Notification{
    @Override
    public void send(String message) {
        System.out.println("Отправка EMAIL: " + message);
    }
}
