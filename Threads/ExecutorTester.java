package Threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTester {
    public static void main(String[] args) {
        ExecutorService myExecutor = Executors.newSingleThreadExecutor();
        CountDownLatch myLatch = new CountDownLatch(1);

/*         Thread myThread = new Thread(() ->
            System.out.println("Wierzchołek stosu!"));
        
        myThread.start(); */
        
/*         myExecutor.execute(() ->
            System.out.println("Wierzchołek stosu!")); */

        //myExecutor.execute(() -> sleepAndRun());

        myExecutor.execute(() -> waitForLatchThenPrint(myLatch));

        System.out.println("Z powrotem w metodzie main()");

        myLatch.countDown();

        myExecutor.shutdown();
    }

    public static void sleepAndRun() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Wierzchołek stosu!");
    }

    public static void waitForLatchThenPrint(CountDownLatch latch) {
        try {
            latch.await();
        } catch ( InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Wierzchołek stosu!");
    }
}
