package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDownTester {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadsPool = Executors.newFixedThreadPool(2);

        threadsPool.execute(new LongTask("Long task"));
        threadsPool.execute(new ShortTask("Short task"));
        threadsPool.shutdown();

        try {
            boolean isFinished = threadsPool.awaitTermination(3, TimeUnit.SECONDS);
            System.out.println("Finished? " + isFinished);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        threadsPool.shutdownNow();
    }
}

class LongTask implements Runnable {
    private String taskTitle;
    
    LongTask(String title) {
        taskTitle = title;
    }
        
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(taskTitle);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

class ShortTask implements Runnable {
    private String taskTitle;
    
    ShortTask(String title) {
        taskTitle = title;
    }
        
    public void run() {
        System.out.println(taskTitle);
    }
}