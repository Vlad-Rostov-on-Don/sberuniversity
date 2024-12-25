package homeworke.lesson11b;

public class Main {
    public static void main(String[] args) {
        // FixedThreadPool
        System.out.println("FixedThreadPool:");
        ThreadPool fixedPool = new FixedThreadPool(3);
        fixedPool.start();
        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            fixedPool.execute(() -> {
                System.out.println("Фиксированная задача " + taskNumber + " выполняется с помощью " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        ((FixedThreadPool) fixedPool).shutdown();

        // ScalableThreadPool
        System.out.println("\nScalableThreadPool:");
        ThreadPool scalablePool = new ScalableThreadPool(2, 5);
        scalablePool.start();
        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            scalablePool.execute(() -> {
                System.out.println("Масштабируемая задача " + taskNumber + " выполняется с помощью " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        ((ScalableThreadPool) scalablePool).shutdown();
    }
}
