package homeworke.lesson2a;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("январь");
        stringList.add("февраль");
        stringList.add("март");
        stringList.add("апрель");
        stringList.add("май");
        stringList.add("июнь");
        stringList.add("июль");
        stringList.add("август");
        stringList.add("сентябрь");
        stringList.add("октябрь");
        stringList.add("ноябрь");
        stringList.add("декабрь");
        stringList.add("январь");
        stringList.add("февраль");
        stringList.add("март");
        stringList.add("январь");
        stringList.add("январь");
        stringList.add("февраль");

        Set<String> setString = new HashSet<>();
        ArrayList<String> listString = new ArrayList<>();
        stringList.forEach(n -> {
            if (!setString.add(n)) {
                listString.add(n);
            }
        });
        System.out.println(setString);

        HashMap<String, Integer> number = new HashMap<>();
        for (String string : stringList) {
            if (!number.containsKey(string)) {
                number.put(string, 1);
            } else {
                int count = number.get(string);
                number.put(string, count + 1);
            }
        }
        for (Map.Entry<String, Integer> entry: number.entrySet()) {
            System.out.println(entry);
        }
    }
}
