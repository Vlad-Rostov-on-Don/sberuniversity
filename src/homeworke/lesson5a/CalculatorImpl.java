package homeworke.lesson5a;

public class CalculatorImpl implements Calculator{

    @Override
    public int calc(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Факториал отрицательного числа не существует");
        }
        int result = 1;
        for (int x = 1; x <= number; x++)
            result *= x;
        return result;
    }
}
