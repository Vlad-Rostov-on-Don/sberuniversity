package homeworke.lesson9;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> someCollection = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 19),
                new Person("Charlie", 35)
        );

        Map<String, Person> result = Streams.of(someCollection)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(p.getName(), p.getAge() + 30))
                .toMap(Person::getName, p -> p);

        result.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
