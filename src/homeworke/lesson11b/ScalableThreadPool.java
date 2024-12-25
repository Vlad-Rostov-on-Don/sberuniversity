package homeworke.lesson11b;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScalableThreadPool implements ThreadPool {
    private final int minThreads;
    private final int maxThreads;
    private final BlockingQueue<Runnable> taskQueue;
    private final AtomicInteger currentThreads;
    private final ThreadGroup threadGroup;
    private volatile boolean isRunning;

    public ScalableThreadPool(int minThreads, int maxThreads) {
        if (minThreads <= 0 || maxThreads < minThreads) {
            throw new IllegalArgumentException("Недопустимая конфигурация пула потоков");
        }
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.currentThreads = new AtomicInteger(0);
        this.threadGroup = new ThreadGroup("ScalableThreadPool");
        this.isRunning = false;
    }

    @Override
    public void start() {
        if (isRunning) {
            throw new IllegalStateException("Пул потоков уже запущен");
        }
        isRunning = true;
        for (int i = 0; i < minThreads; i++) {
            createWorker();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isRunning) {
            throw new IllegalStateException("Пул потоков не запущен. Сначала вызовите start().");
        }
        taskQueue.offer(runnable);
        synchronized (this) {
            if (currentThreads.get() < maxThreads && !taskQueue.isEmpty()) {
                createWorker();
            }
        }
    }

    public void shutdown() {
        isRunning = false;
        threadGroup.interrupt();
    }

    private void createWorker() {
        if (currentThreads.incrementAndGet() <= maxThreads) {
            new Worker(threadGroup, "Worker-" + currentThreads.get()).start();
        } else {
            currentThreads.decrementAndGet();
        }
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            while (isRunning || !taskQueue.isEmpty()) {
                try {
                    Runnable task = taskQueue.poll();
                    if (task != null) {
                        task.run();
                    } else if (currentThreads.get() > minThreads) {
                        currentThreads.decrementAndGet();
                        break;
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
