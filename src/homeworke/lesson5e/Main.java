package homeworke.lesson5e;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = PerformanceProxy.newInstance(new CalculatorImpl());
        System.out.println(calculator.calc(3));
    }
}
    class CalculatorImpl implements Calculator {
        @Override
        public int calc(int number) {
            int factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
            return factorial;
        }
    }