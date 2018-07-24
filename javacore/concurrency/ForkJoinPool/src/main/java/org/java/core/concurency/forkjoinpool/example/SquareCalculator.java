package org.java.core.concurency.forkjoinpool.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {

//    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(input);
            return input * input;
        });
    }

    public void shutdown() {
        if (!executor.isTerminated()) {
            executor.shutdown();
        }
    }
}
