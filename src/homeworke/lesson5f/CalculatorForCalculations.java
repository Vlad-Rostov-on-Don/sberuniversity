package homeworke.lesson5f;

public class CalculatorForCalculations implements Calculator{

    @Override
    public int calculateNumber(int number) {
        System.out.println("Вычисление куба числа: " + number);

        return number * number * number;
    }
}
