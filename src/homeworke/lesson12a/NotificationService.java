package homeworke.lesson12a;

public class NotificationService {
    public void sendNotification(String type, String message) {
        if ("EMIL".equalsIgnoreCase(type)) {
            System.out.println("Отправка EMAIL " + message);
        } else if ("SMS".equalsIgnoreCase(type)) {
            System.out.println("Отправка SMS " + message);
        } else {
            System.out.println("Неизвестный тип уведомления");
        }
    }
}
