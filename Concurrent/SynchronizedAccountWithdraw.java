package Concurrent;

import java.util.concurrent.*;

public class SynchronizedAccountWithdraw {
    public static void main (String[] args) {
        //BankAccountSynchronized account = new BankAccountSynchronized();
        AtomicAccount account = new AtomicAccount();
        UsersSynchronized robert = new UsersSynchronized("Robert", account, 50);
        UsersSynchronized monika = new UsersSynchronized("Monika", account, 100);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(robert);
        executor.execute(monika);
        executor.shutdown();
    }
}

class UsersSynchronized implements Runnable {
    private final String name;
    //private final BankAccountSynchronized account;
    private final AtomicAccount account;
    private final int amountToSpend;
    //UsersSynchronized(String name, BankAccountSynchronized account, int amountToSpend) {
    UsersSynchronized(String name, AtomicAccount account, int amountToSpend) {
        this.name = name;
        this.account = account;
        this.amountToSpend = amountToSpend;
    }

    public void run() {
        doShopping(amountToSpend);
    }

    private void doShopping(int amount) {
        System.out.println(name + " is trying to spend some money...");
        account.spend(name, amount);
        System.out.println(name + " ended shopping.");
    }    
}

class BankAccountSynchronized {
    private int balance = 100;
    public int getBalance() {
        return balance;
    }

    public synchronized void spend(String name, int amount) {
        if (getBalance() >= amount) {
            balance = balance - amount;
            if (balance < 0) {
                System.out.println("Account limit exceeded!");
            }
        } else {
            System.out.println("Sorry " + name + ", you do not have enough money");
        }
    }
}
