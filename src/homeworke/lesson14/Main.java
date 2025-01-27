package homeworke.lesson14;

public class Main {
    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy();
        ICalculator calculator = cacheProxy.cache(new Calculator());

        System.out.println(calculator.fibonachi(10)); // Считает и сохраняет в H2
        System.out.println(calculator.fibonachi(10)); // Загружает из H2
    }
}
