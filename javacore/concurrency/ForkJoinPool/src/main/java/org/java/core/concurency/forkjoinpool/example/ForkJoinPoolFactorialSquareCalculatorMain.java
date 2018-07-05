package org.java.core.concurency.forkjoinpool.example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.google.common.collect.Lists.newArrayList;

public class ForkJoinPoolFactorialSquareCalculatorMain {
    private static final Logger LOGGER = LogManager.getLogger(ForkJoinPoolFactorialSquareCalculatorMain.class);

    public static void main(String[] args) {
        List<Integer> toBeCalculated = newArrayList(1001, 101, 1301, 11, 14, 1210, 940, 780);
        ForkJoinPool forkJoinPool = createForkJoinPool();

        Map<Integer, Long> calculatedTasks = createPullingEventsTask(forkJoinPool, toBeCalculated);
        calculatedTasks.entrySet().stream()
                .forEach(e -> LOGGER.info("value <{}> factorial is <{}>", e.getKey(), e.getValue()));

    }

    static Map<Integer, Long> createPullingEventsTask(final ForkJoinPool forkJoinPool, List<Integer> toBeCalculated) {
        return toBeCalculated
                // to check difference if stream is parallel or not
                // but every FactorialSquareCalculator has inner parallel handling
                .parallelStream()
//                .stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                input -> calculateAndGetResult(forkJoinPool, input)
                        )
                );
    }

    private static Long calculateAndGetResult(ForkJoinPool forkJoinPool, Integer input) {
        Long result = -1L;
        try {
            result = forkJoinPool.submit(new FactorialSquareCalculator(input)).get();
            LOGGER.info("value <{}> factorial is <{}>", input, result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    static ForkJoinPool createForkJoinPool() {
        final int parallelism = 10;
        final ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism);
        LOGGER.debug("Created a thread pool with size [{}]", forkJoinPool.getParallelism());
        LOGGER.debug("Thread pool with active threads [{}]", forkJoinPool.getActiveThreadCount());
        return forkJoinPool;
    }

}
