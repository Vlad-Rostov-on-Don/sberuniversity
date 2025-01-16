package homeworke.lesson8;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service{
    @Override
    public List<String> doHardWork(String item, double value) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> result = new ArrayList<>();
        result.add(item + " " + value);
        return result;
    }

    @Override
    public List<String> anotherTask(String item) {
        List<String> result = new ArrayList<>();
        for (int x = 0; x < 1_000_000; x++) {
            result.add(item + " " + x);
        }
        return result;
    }
}
