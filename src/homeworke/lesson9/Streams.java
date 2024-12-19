package homeworke.lesson9;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams<T> {
    private final List<T> source;

    private Streams(List<T> source) {
        this.source = new ArrayList<>(source);
    }

    public static <T> Streams<T> of(List<? extends T> source) {
        return (Streams<T>) new Streams<>(source);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        source.removeIf(element -> !predicate.test(element));
        return this;
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> transformer) {
        Objects.requireNonNull(transformer);
        List<R> transformed = source.stream()
                .map(transformer)
                .collect(Collectors.toList());
        return new Streams<>(transformed);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
        Objects.requireNonNull(keyMapper);
        Objects.requireNonNull(valueMapper);
        return source.stream()
                .collect(Collectors.toMap(keyMapper, valueMapper));
    }
}