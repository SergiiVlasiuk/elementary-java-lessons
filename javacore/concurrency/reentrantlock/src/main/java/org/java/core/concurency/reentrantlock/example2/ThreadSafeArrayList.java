package org.java.core.concurency.reentrantlock.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadSafeArrayList<E> {
    private static final Logger LOGGER = LogManager.getLogger(ThreadSafeArrayList.class);
    private static volatile int i = 0;
    private final Lock lock = new ReentrantLock();
    private final List<E> list = new ArrayList<E>();

    public static void main(String[] args) {
        final ThreadSafeArrayList<String> lockExample = new ThreadSafeArrayList<String>();
        Runnable syncThread = getRunnable(lockExample);

        Runnable lockingThread = getRunnable(lockExample);
        Thread t1 = new Thread(syncThread, "syncThread");
        Thread t2 = new Thread(lockingThread, "lockThread");
        t1.start();
        t2.start();
    }

    private static Runnable getRunnable(ThreadSafeArrayList<String> lockExample) {
        return () -> {
            while (i < 6) {
                lockExample.set(String.valueOf(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void set(E o) {
        lock.lock();
        try {
            i++;
            list.add(o);
            LOGGER.info("Adding element by thread: " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }

}
