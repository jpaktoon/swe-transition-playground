package multithread;

import java.time.Clock;
import java.time.DateTimeException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadExecutor {

    static long process(int aSecWaitTime, int bSecWaitTime) {
        Clock clock = Clock.systemUTC();
        final long startTimeMs = clock.millis();

        // Run the hold(aSecWaitTime) in the main thread
        final String syncResult = hold(aSecWaitTime, false, "Thread A");

        // Create a CompletableFuture for the hold(bSecWaitTime) task
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                hold(bSecWaitTime, true, "Thread B");
            } catch (DateTimeException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
        futureB.orTimeout(5, TimeUnit.SECONDS);

        futureB.thenAccept(asyncResult -> {
            // Compare results (or do something with them)
            System.out.println(syncResult); // syncResult is accessible here
        }).exceptionally(ex -> {
            if (ex instanceof TimeoutException) {
                System.out.println("Async API call timed out: " + ex.getMessage());
            } else {
                System.out.println("Async API call failed: " + ex.getMessage());
            }
            return null;
        });

//        try {
//            // Wait for futureB to complete with a timeout of 5 seconds
//            futureB.get();
//        } catch (Exception e) {
//            System.out.println("Got exception: " + e.getMessage());
//        }

        return clock.millis() - startTimeMs;
    }

    static String hold(int time, boolean needException, String tName) {
        try {
            Clock clock = Clock.systemUTC();
            final long startTimeMs = clock.millis();
            Thread.sleep(time * 1000L);
            if (needException)
            {
                System.out.println("Time in thread B: " + (clock.millis() - startTimeMs));
                throw new DateTimeException("just custom one");
            }
            return tName;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1) elapsedTimeMs : " + process(1, 2)); // expect 2000
        System.out.println("2) elapsedTimeMs : " + process(2, 1)); // expect 2000
        System.out.println("3) elapsedTimeMs : " + process(3, 1000000000)); // expect 5000
        System.out.println("4) elapsedTimeMs : " + process(8, 3)); // expect 8000
        Thread.sleep(5000);
    }
}
