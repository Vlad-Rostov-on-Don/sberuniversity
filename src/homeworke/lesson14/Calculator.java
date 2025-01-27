package homeworke.lesson14;

import java.util.ArrayList;
import java.util.List;

public class Calculator implements ICalculator{
    @Override
    @Cachable(H2DB.class)

    public List<Integer> fibonachi(int n) {
        System.out.println("Вычисление Фибоначчи для " + n);
        List<Integer> result = new ArrayList<>();
        int a = 0, b = 1;
        for (int x = 0; x < n; x++) {
            result.add(a);
            int temp = a + b;
            a = b;
            b = temp;
        }
        return result;
    }
}
