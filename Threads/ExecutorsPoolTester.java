package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsPoolTester {
    public static void main(String[] args) {
        ExecutorService threadsPool = Executors.newFixedThreadPool(2);
        threadsPool.execute(() -> taskToDo("Task no. 1"));
        threadsPool.execute(() -> taskToDo("Task no. 2"));
        threadsPool.shutdown();
    }

    public static void taskToDo(String taskTitle) {
        for (int i = 0; i < 25; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(String.format("Task %s in thread %s", taskTitle, threadName));
        }
    }
}
