package homeworke.lesson14;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Base64;

public class CacheProxy {
    public <T> T cache(T service) {
        return (T) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Cachable cachable = method.getAnnotation(Cachable.class);
                    if (cachable != null) {
                        return handleCaching(service, method, args, cachable);
                    }
                    return method.invoke(service, args);
                }
        );
    }

    private Object handleCaching(Object service, Method method, Object[] args, Cachable cachable) throws Throwable {
        Source source = cachable.value().getDeclaredConstructor().newInstance();
        String key = generateKey(method, args);

        if (source.contains(key)) {
            return source.load(key, method.getReturnType());
        }

        Object result = method.invoke(service, args);

        source.save(key, result);
        return result;
    }

    private String generateKey(Method method, Object[] args) {
        StringBuilder keyBuilder = new StringBuilder(method.getName());

        for (Object arg : args) {
            keyBuilder.append("_").append(arg);
        }
        return Base64.getEncoder().encodeToString(keyBuilder.toString().getBytes());
    }
}
