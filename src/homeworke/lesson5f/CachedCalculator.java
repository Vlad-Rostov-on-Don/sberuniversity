package homeworke.lesson5f;

import java.util.HashMap;
import java.util.Map;

public class CachedCalculator implements Calculator{

    private Map<Integer, Integer> cash = new HashMap<>();
    private Calculator calculator;

    public CachedCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculateNumber(int number) {
        if(!cash.containsKey(number)) {
            int result = calculator.calculateNumber(number);
            cash.put(number, result);
            return result;
        } else {
            System.out.println("Возвращаем из кэша для: " + number);
        }
        return cash.get(number);
    }
}
