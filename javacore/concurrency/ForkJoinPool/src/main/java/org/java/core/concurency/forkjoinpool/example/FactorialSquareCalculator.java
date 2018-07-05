package org.java.core.concurency.forkjoinpool.example;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FactorialSquareCalculator extends RecursiveTask<Long> {
    private static final Logger LOGGER = LogManager.getLogger(FactorialSquareCalculator.class);
    private static final AtomicInteger MONITOR = new AtomicInteger();
    private Long n;

    public FactorialSquareCalculator(Integer n) {
        this(Long.valueOf(n));
    }

    private FactorialSquareCalculator(Long n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 1) {
            return n;
        }

        try {
            // emulate time spending for hard calculation
            synchronized (MONITOR) {
                MONITOR.wait(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FactorialSquareCalculator calculator
                = new FactorialSquareCalculator(n - 1);

        calculator.fork();

        long result = n * n + calculator.join();
//        LOGGER.debug(result);
        return result;
    }

}
