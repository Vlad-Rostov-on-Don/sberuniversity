package homeworke;

import homeworke.lesson10.FactorialTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        Path filePath = Path.of("numbers.txt");

        try {

            List<String> lines = Files.readAllLines(filePath);
            ExecutorService executorService = Executors.newFixedThreadPool(4); // Пул из 4 потоков

            for (String line : lines) {
                int number = Integer.parseInt(line.trim());
                if (number > 0) {

                    FactorialTask task = new FactorialTask(number);
                    executorService.submit(task);
                }
            }

            executorService.shutdown();

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка обработки числа: " + e.getMessage());
        }
    }
}