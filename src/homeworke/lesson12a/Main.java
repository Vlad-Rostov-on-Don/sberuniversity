package homeworke.lesson12a;

public class Main {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.sendNotification("EMAIL","Приветствие по электронной почте!");
        service.sendNotification("SMS", "Приветствие по SMS!");
        service.sendNotification("PUSH", "Это не сработает");
    }
}
