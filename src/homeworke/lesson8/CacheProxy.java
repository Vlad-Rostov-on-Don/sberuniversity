package homeworke.lesson8;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CacheProxy {
    private final File rootFolder;
    private final Map<Method, Map<List<Object>, Object>> inMemoryCache = new HashMap<>();

    public CacheProxy(File rootFolder) {
        this.rootFolder = rootFolder;
        if (!rootFolder.exists()) {
            rootFolder.mkdirs();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T cache(T service) {
        Class<?> serviceClass = service.getClass();
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                serviceClass.getInterfaces(),
                (proxy, method, args) -> {
                    Cache cacheAnnotation = method.getAnnotation(Cache.class);
                    if (cacheAnnotation == null) {
                        return method.invoke(service, args);
                    }

                    List<Object> key = buildKey(args, cacheAnnotation.identityBy());
                    if (cacheAnnotation.cacheType() == CacheType.IN_MEMORY) {
                        return handleInMemoryCache(method, key, args, service);
                    } else {
                        return handleFileCache(method, key, args, service, cacheAnnotation);
                    }
                });
    }

    private List<Object> buildKey(Object[] args, Class<?>[] identityBy) {
        List<Object> key = new ArrayList<>();
        if (identityBy.length == 0) {
            key.addAll(Arrays.asList(args));
        } else {
            for (Object arg : args) {
                for (Class<?> cls : identityBy) {
                    if (cls.isInstance(arg)) {
                        key.add(arg);
                        break;
                    }
                }
            }
        }
        return key;
    }

    private Object handleInMemoryCache(Method method, List<Object> key, Object[] args, Object service) throws Throwable {
        inMemoryCache.putIfAbsent(method, new HashMap<>());
        Map<List<Object>, Object> methodCache = inMemoryCache.get(method);

        if (methodCache.containsKey(key)) {
            return methodCache.get(key);
        }

        Object result = method.invoke(service, args);
        if (result instanceof List<?> list && list.size() > method.getAnnotation(Cache.class).listLimit()) {
            result = list.subList(0, method.getAnnotation(Cache.class).listLimit());
        }
        methodCache.put(key, result);
        return result;
    }

    private Object handleFileCache(Method method, List<Object> key, Object[] args, Object service, Cache cacheAnnotation) throws Throwable {
        String failName = cacheAnnotation.failNamePrefix().isEmpty()
                ? method.getName()
                : cacheAnnotation.failNamePrefix();
        File file = new File(rootFolder, failName + key.hashCode() + ".cache");
        if (file.exists()) {
            return deserializeFromFile(file, cacheAnnotation.zip());
        }

        Object result = method.invoke(service, args);
        if (result instanceof List<?> list && list.size() > cacheAnnotation.listLimit()) {
            result = list.subList(0, cacheAnnotation.listLimit());
        }
        serializeToFile(result, file, cacheAnnotation.zip());
        return result;
    }

    private Object deserializeFromFile(File file, boolean zip) throws IOException, ClassNotFoundException {
        try (InputStream fis = new FileInputStream(file);
             InputStream is = zip ? new GZIPInputStream(fis) : fis;
             ObjectInputStream ois = new ObjectInputStream(is)) {
            return ois.readObject();
        }
    }

    private void serializeToFile(Object result, File file, boolean zip) throws IOException {
        if (!(result instanceof Serializable)) {
            throw new IllegalArgumentException("Result is not serializable: " + result);
        }
        try (OutputStream fos = new FileOutputStream(file);
             OutputStream os = zip ? new GZIPOutputStream(fos) : fos;
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(result);
        }
    }
}