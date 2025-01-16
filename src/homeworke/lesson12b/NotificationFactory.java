package homeworke.lesson12b;

public class NotificationFactory {
    public static Notification createNotification(String type) {
        switch (type.toUpperCase()) {
            case "Email":
                return new  EmailNotification();
            case "SMS":
                return new SMSNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Неизвестный тип уведомления");
        }
    }
}
