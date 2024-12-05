package homeworke.lesson5b;
import java.lang.reflect.Method;

public class OutputToConsole {
    public static void main(String[] args) {

        Class<?> klass = String.class;

        printAllMethods(klass);
    }

    private static void printAllMethods(Class<?> klass) {
        System.out.println("Методы класса " + klass.getName() + ":");

        // Получаем массив всех методов данного класса
        Method[] methods = klass.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method.toString());
        }

        // Если у класса есть суперкласс, рекурсивно вызываем метод для него
        if (klass.getSuperclass() != null) {
            printAllMethods(klass.getSuperclass());
        }
    }
}