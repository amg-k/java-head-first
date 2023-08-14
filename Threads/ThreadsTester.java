package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsTester {
    public static void main(String[] args) {
        Runnable threadTask = new MyTask();
        //Thread myThread = new Thread(threadTask);

        //myThread.start();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(threadTask);

        System.out.println(
            Thread.currentThread().getName() + 
            ": Z powrotem w metodzie main()");
        
        //Thread.dumpStack();

        executor.shutdown();
    }
}

class MyTask implements Runnable {
    public void run() {
        doIt();
    }

    public void doIt() {
        nextTask();
    }

    public void nextTask() {
        System.out.println(Thread.currentThread().getName() +
        ": Wierzchołek stosu!");
        Thread.dumpStack();
    }
}
