package homeworke.lesson5e;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceProxy implements InvocationHandler {

    private final Object target;

    public PerformanceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();

        Object result = method.invoke(target, args);

        if (method.isAnnotationPresent(Metric.class)) {
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            System.out.printf("Время работы метода %s: %d нс\n",
                    method.getName(), elapsedTime);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(T instance) {
        Class<?>[] interfaces = instance.getClass().getInterfaces();
        return (T) java.lang.reflect.Proxy.newProxyInstance(
                instance.getClass().getClassLoader(),
                interfaces,
                new PerformanceProxy(instance));
    }
}
