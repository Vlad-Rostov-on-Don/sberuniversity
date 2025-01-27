package homeworke.lesson14;

public interface Source {
    void save(String key, Object value);

    <T> T load(String key, Class<T> type);

    boolean contains(String key);
}
