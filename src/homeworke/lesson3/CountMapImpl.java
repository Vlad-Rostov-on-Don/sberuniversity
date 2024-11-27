package homeworke.lesson3;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T t) {
        map.put(t, map.getOrDefault(t, 0) +1 );
    }

    @Override
    public int getCount(T t) {
        return map.getOrDefault(t, 0);
    }

    @Override
    public int remove(T t) {
        Integer count = map.remove(t);
        return count == null ? 0 : count;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        for (T key : source.toMap().keySet()) {
            int count = source.getCount(key);
            map.put(key, map.getOrDefault(key, 0) + count);
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(map);
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.clear();
        destination.putAll(map);
    }
}
