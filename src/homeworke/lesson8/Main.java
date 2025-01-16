package homeworke.lesson8;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        CacheProxy proxy = new CacheProxy(new File("cache"));
        Service service = proxy.cache(new ServiceImpl());

        System.out.println(service.doHardWork("work1", 10));
        System.out.println(service.doHardWork("work2", 5));
        System.out.println(service.doHardWork("work1", 10));
    }
}
