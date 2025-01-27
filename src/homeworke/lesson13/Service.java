package homeworke.lesson13;

import java.util.List;

public interface Service {
    @homeworke.lesson13.Cache(cacheType = homeworke.lesson13.CacheType.FAIL, failNamePrefix = "data", zip = true, identityBy = {homeworke.lesson13.Service.class})
    List<String> doHardWork(String item, double value);

    @Cache(cacheType = CacheType.IN_MEMORY, listLimit = 100_000)
    List<String> anotherTask(String item);
}
