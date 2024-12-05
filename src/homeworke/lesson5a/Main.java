package homeworke.lesson5a;

public class Main {
    public static void main(String[] args) {

    Calculator calculator = new CalculatorImpl();
    int factorial = calculator.calc(5);
        System.out.println(factorial);
    }
}
