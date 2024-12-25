package homeworke.lesson11a;

public interface ThreadPool {
    void start();
    void execute(Runnable runnable);
}
