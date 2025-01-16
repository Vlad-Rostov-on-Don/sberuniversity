package homeworke.lesson12b;

public class SMSNotification extends Notification{
    @Override
    public void send(String message) {
        System.out.println("Отправка SMS: " + message);
    }
}
