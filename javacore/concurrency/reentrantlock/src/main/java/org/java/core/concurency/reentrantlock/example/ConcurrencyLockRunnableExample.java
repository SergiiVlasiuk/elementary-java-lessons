package org.java.core.concurency.reentrantlock.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.core.concurency.AnyService;

public class ConcurrencyLockRunnableExample implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(ConcurrencyLockRunnableExample.class);

    private AnyService resource;
    private ReentrantLock lock;

    public ConcurrencyLockRunnableExample(AnyService r) {
        this.resource = r;
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                resource.doSomething();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //release lock
            if (lock.isHeldByCurrentThread()) {
                LOGGER.info(Thread.currentThread().getName() + " unlock resource obj");
                lock.unlock();
            }
        }
        resource.doLogging();
    }

}
