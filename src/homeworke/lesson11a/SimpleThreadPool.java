package homeworke.lesson11a;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadPool implements ThreadPool{
    private final int threadCount;
    private final BlockingQueue<Runnable> taskQueue;
    private final WorkerThread[] workers;
    volatile boolean isRunning;

    public SimpleThreadPool(int threadCount) {
        this.threadCount = threadCount;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new WorkerThread[threadCount];
        this.isRunning = false;
    }

    @Override
    public void start() {
        if (isRunning) {
            throw new IllegalStateException("Пул потоков уже запущен");
        }
        isRunning = true;
        for (int x = 0; x < threadCount; x++) {
            workers[x] = new WorkerThread(taskQueue, this);
            workers[x].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isRunning) {
            throw new IllegalStateException("Пул потоков не запущен. Сначала вызовите start().");
        }
        taskQueue.offer(runnable);
    }

    public void shutdown() {
        isRunning = false;
        for (WorkerThread worker : workers) {
            worker.interrupt();
        }
    }
}
