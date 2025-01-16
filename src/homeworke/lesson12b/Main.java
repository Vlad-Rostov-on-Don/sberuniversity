package homeworke.lesson12b;

public class Main {
    public static void main(String[] args) {
        Notification email = NotificationFactory.createNotification("EMAIL");
        email.send("Приветствие по электронной почте!");

        Notification sms = NotificationFactory.createNotification("SMS");
        sms.send("Приветствие по SMS!");

        Notification push = NotificationFactory.createNotification("PUSH");
        push.send("Привет через Push-уведомление!");
    }
}
