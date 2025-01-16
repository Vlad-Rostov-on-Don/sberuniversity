package homeworke.lesson12b;

public class PushNotification extends Notification{
    @Override
    public void send(String message) {
        System.out.println("Отправка Push-уведомлений: " + message);
    }
}
