package homeworke.lesson11b;

public interface ThreadPool {
    void start();
    void execute(Runnable runnable);
}
