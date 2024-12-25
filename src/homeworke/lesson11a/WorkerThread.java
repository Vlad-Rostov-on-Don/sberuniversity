package homeworke.lesson11a;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread{
    private final BlockingQueue<Runnable> taskQueue;
    private final SimpleThreadPool threadPool;

    public WorkerThread(BlockingQueue<Runnable> taskQueue, SimpleThreadPool threadPool) {
        this.taskQueue = taskQueue;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while (threadPool.isRunning || !taskQueue.isEmpty()) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                if (!threadPool.isRunning) {
                    break;
                }
                Thread.currentThread().interrupt();
            }
        }
    }
}

