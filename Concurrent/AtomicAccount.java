package Concurrent;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicAccount {
    private final AtomicInteger accountBalance = new AtomicInteger(100);

    public int getBalance() {
        return accountBalance.get();
    }

    public void spend(String name, int amount) {
        int initialState = accountBalance.get();
        if (initialState >= amount) {
            boolean isSuccess = accountBalance.compareAndSet(initialState, initialState - amount);
            if (!isSuccess) {
                System.out.println("A problem with your bank account has occurred...");
            }
        } else {
            System.out.println(String.format("Sorry %s, you do not have enough money on your bank account.", name));
        }
    }
}
