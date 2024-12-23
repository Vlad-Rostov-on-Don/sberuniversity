package homeworke.lesson10;

import java.math.BigInteger;

public class FactorialCalculator {
    public static BigInteger calculate(int number) {
        BigInteger result = BigInteger.ONE;
        for (int x = 2; x <= number; x++) {
            result = result.multiply(BigInteger.valueOf(x));
        }
        return result;
    }
}
