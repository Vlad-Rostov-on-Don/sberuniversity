package homeworke.lesson5f;

public class CacheProxy {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorForCalculations();
        CachedCalculator cachedCalculator = new CachedCalculator(calculator);

        System.out.println("Результат для 3: " + cachedCalculator.calculateNumber(3));
        System.out.println("Результат для 5: " + cachedCalculator.calculateNumber(5));
        System.out.println("Результат для 3 again: " + cachedCalculator.calculateNumber(3));
        System.out.println("Результат для 4: " + cachedCalculator.calculateNumber(4));
    }
}
