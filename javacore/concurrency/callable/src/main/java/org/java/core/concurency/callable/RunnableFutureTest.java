package org.java.core.concurency.callable;

import java.util.Random;
import java.util.concurrent.FutureTask;

public class RunnableFutureTest {
    public static void main(String[] args) throws Exception
    {

        // FutureTask is a concrete class that
        // implements both Runnable and Future
        FutureTask[] randomNumberTasks = new FutureTask[5];

        for (int i = 0; i < 5; i++)
        {
            Runnable runnable = new RunnableExample();

            Integer result = i;
            // Create the FutureTask with Callable
            randomNumberTasks[i] = new FutureTask(runnable, result);

            // As it implements Runnable, create Thread
            // with FutureTask
            Thread t = new Thread(randomNumberTasks[i]);
            t.start();
        }

        for (int i = 0; i < 5; i++)
        {
            // As it implements Future, we can call get()
            System.out.println(randomNumberTasks[i].get());

            // This method blocks till the result is obtained
            // The get method can throw checked exceptions
            // like when it is interrupted. This is the reason
            // for adding the throws clause to main
        }
    }
}

class RunnableExample implements Runnable {

    public void run() {
        Random generator = new Random();
        Integer randomNumber = generator.nextInt(5);

        try {
            Thread.sleep(randomNumber * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}