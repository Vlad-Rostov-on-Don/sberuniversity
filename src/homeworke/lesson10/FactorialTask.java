package homeworke.lesson10;

import java.math.BigInteger;

public class FactorialTask implements Runnable{
    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        BigInteger factorial = FactorialCalculator.calculate(number);
        System.out.println("Факториал числа " + number + " равен " + factorial);
    }
}
