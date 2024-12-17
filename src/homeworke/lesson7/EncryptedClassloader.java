package homeworke.lesson7;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EncryptedClassloader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassloader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace('.', File.separatorChar) + " .class";
        File classFile = new File(dir, classPath);

        if (!classFile.exists()) {
            throw new ClassNotFoundException("Файл класса не найден: " + classFile.getAbsolutePath());
        }

        try {
            byte[] encryptedBytes = Files.readAllBytes(classFile.toPath());

            byte[] decryptedBytes = decrypt(encryptedBytes);

            return defineClass(name, decryptedBytes, 0, decryptedBytes.length);
        } catch (IIOException e) {
            throw new ClassNotFoundException("Ошибка при чтении файла класса: " + classFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] decrypt(byte[] encryptedBytes) {
        int decryptKey = 0;
        for (char x : key.toCharArray()) {
            decryptKey += x;
        }

        byte[] decryptedBytes = new byte[encryptedBytes.length];
        for (int y = 0; y <encryptedBytes.length; y++) {
            decryptedBytes[y] = (byte) (encryptedBytes[y] - decryptKey);
        }
        return decryptedBytes;
    }

    public static void main(String[] args) {
        try {
            File classesDir = new File("путь к классам");
            EncryptedClassloader classloader = new EncryptedClassloader("секретный ключ", classesDir, ClassLoader.getSystemClassLoader());
            Class<?> klass = classloader.loadClass("com.example.MyEncryptedClass");
            System.out.println("Загруженный класс: " + klass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
