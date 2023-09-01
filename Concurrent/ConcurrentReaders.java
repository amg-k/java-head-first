package Concurrent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentReaders {
    public static void main(String[] args) {
        List<SmallTalk> talkLog = new CopyOnWriteArrayList<>(); 
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> talkLog.add(new SmallTalk("Czołem!")));
            executor.execute(() -> System.out.println(Thread.currentThread().getName() + talkLog));
            executor.execute(() -> System.out.println(Thread.currentThread().getName() + talkLog));
        }
        executor.shutdown();
    }
}

final class SmallTalk {
    private final String message;
    private final LocalDateTime timeMarker;

    public SmallTalk(String message) {
        this.message = message;
        timeMarker = LocalDateTime.now();
    }

    public String toString() {
        String time = timeMarker.toString();
        return time + " " + message;
    }
    
}
