package homeworke.lesson11a;

public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new SimpleThreadPool(4);

        threadPool.start();

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            threadPool.execute(() -> {
                System.out.println("Задача " + taskNumber + " выполняется с помощью " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ((SimpleThreadPool) threadPool).shutdown();
        System.out.println("Пул потоков закрыт.");
    }
}
