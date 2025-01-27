package homeworke.lesson13;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        CacheProxy proxy = new CacheProxy(new File("cache"));
        Service service = proxy.cache(new ServiceImpl());

        // Параллельный вызов методов с кешированием
        Runnable task1 = () -> {
            System.out.println(service.doHardWork("task1", 100));
        };

        Runnable task2 = () -> {
            System.out.println(service.doHardWork("task2", 200));
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
