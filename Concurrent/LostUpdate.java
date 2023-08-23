package Concurrent;

import java.util.concurrent.*;

public class LostUpdate {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        AccountBalance balance = new AccountBalance();
        for (int i = 0; i < 10000; i++) {
            pool.execute(() -> balance.increment());
        }
        pool.shutdown();
        if (pool.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Account balance = " + balance.balance);
        }
    }
}

class AccountBalance {
    int balance = 0;

    public synchronized void increment() {
        balance++;
    }
}
