package homeworke.lesson11b;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int threadCount;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;
    private volatile boolean isRunning;

    public FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new Thread[threadCount];
        this.isRunning = false;
    }

    @Override
    public void start() {
        if (isRunning) {
            throw new IllegalStateException("Пул потоков уже запущен");
        }
        isRunning = true;
        for (int i = 0; i < threadCount; i++) {
            workers[i] = new Worker();
            workers[i].start();
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
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }
    private class Worker extends Thread {
        @Override
        public void run() {
            while (isRunning || !taskQueue.isEmpty()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
