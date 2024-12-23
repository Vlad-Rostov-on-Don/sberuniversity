package homeworke.lesson10dop;

import java.util.concurrent.atomic.AtomicBoolean;

public class DifferentOutput {
    public static void main(String[] args) {
        AtomicBoolean isNumberTurn = new AtomicBoolean(true);

        Thread numberThread = new Thread(() -> {
            for (int x = 0; x < 10; x++) {
                while (!isNumberTurn.get()) {
                }
                System.out.print(x);
                isNumberTurn.set(false);
            }
        });

        Thread letterThread = new Thread(() -> {
            for (char c = 'a'; c <= 'j'; c++) {
                while (isNumberTurn.get()) {
                }
                System.out.print(" " + c + " ");
                isNumberTurn.set(true);
            }
        });

        numberThread.start();
        letterThread.start();

        try {
            numberThread.join();
            letterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}