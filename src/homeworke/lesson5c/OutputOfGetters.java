package homeworke.lesson5c;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class OutputOfGetters {
    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.class;

        printGetters(klass);
    }

    private static void printGetters(Class<?> klass) {
        if (klass == null || klass.equals(Object.class)) {
            return;
        }

        System.out.println("Геттеры класса : " + klass.getName());

        Method[] methods = klass.getDeclaredMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                System.out.println(method.toString());
            }
        }

        printGetters(klass.getSuperclass());
    }

    private static boolean isGetter(Method method) {
        // Геттеры обычно начинаются с get или is и не принимают параметров
        String name = method.getName();
        int modifiers = method.getModifiers();
        Class<?> returnType = method.getReturnType();

        return ((name.startsWith("get") && !void.class.equals(returnType))
                || (name.startsWith("is") && boolean.class.equals(returnType)))
                && method.getParameterCount() == 0
                && Modifier.isPublic(modifiers);
    }
}
