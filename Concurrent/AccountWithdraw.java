package Concurrent;

import java.util.concurrent.*;

public class AccountWithdraw {
    public static void main (String[] args) {
        BankAccount account = new BankAccount();
        Users robert = new Users("Robert", account, 50);
        Users monika = new Users("Monika", account, 100);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(robert);
        executor.execute(monika);
        executor.shutdown();
    }
}

class Users implements Runnable {
    private final String name;
    private final BankAccount account;
    private final int amountToSpend;
    Users(String name, BankAccount account, int amountToSpend) {
        this.name = name;
        this.account = account;
        this.amountToSpend = amountToSpend;
    }

    public void run() {
        doShopping(amountToSpend);
    }

    private void doShopping(int amount) {
        synchronized (account) {
            if (account.getBalance() >= amount) {
                System.out.println(name + " is trying to spend some money...");
                account.spend(amount);
                System.out.println(name + " ended shopping.");
            } else {
                System.out.println("Sorry " + name + " ,you do not have enough money");
            }
        }
    }
}

class BankAccount {
    private int balance = 100;
    public int getBalance() {
        return balance;
    }

    public void spend(int amount) {
        balance = balance - amount;
        if (balance < 0) {
            System.out.println("Account limit exceeded!");
        }
    }
}
