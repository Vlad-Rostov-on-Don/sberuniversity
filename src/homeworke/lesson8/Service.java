package homeworke.lesson8;

import java.util.List;

public interface Service {
    @Cache(cacheType = CacheType.FAIL, failNamePrefix = "data", zip = true, identityBy = {Service.class})
    List<String> doHardWork(String item, double value);

    @Cache(cacheType = CacheType.IN_MEMORY, listLimit = 100_000)
    List<String> anotherTask(String item);
}
