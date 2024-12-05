package homeworke.lesson5d;

import java.lang.reflect.Field;

public class СheckingConstants extends MyClass {
    public static void main(String[] args) throws IllegalAccessException {
        checkConstants(MyClass.class);
    }

    private static void checkConstants(Class<?> klass) throws IllegalAccessException {
        Field[] fields = klass.getFields();

        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                String fieldName = field.getName();
                Object value = field.get(null);

                if (!fieldName.equals(value)) {
                    throw new RuntimeException("Значение константы '" + fieldName + "' должно быть равно ее имени");
                }
            }
        }

        System.out.println("Все константы корректны.");
    }
}
