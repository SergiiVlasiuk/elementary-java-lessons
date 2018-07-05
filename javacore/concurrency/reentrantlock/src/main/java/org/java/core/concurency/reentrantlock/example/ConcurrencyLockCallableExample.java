package org.java.core.concurency.reentrantlock.example;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.java.core.concurency.AnyService;

public class ConcurrencyLockCallableExample implements Callable<UUID> {

    private AnyService<UUID> anyService;
    private Lock lock;

    public ConcurrencyLockCallableExample(AnyService<UUID> r) {
        this.anyService = r;
        this.lock = new ReentrantLock();
    }

    @Override
    public UUID call() throws Exception {
        UUID answer = null;
        try {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                answer = anyService.doSomething(UUID.randomUUID());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //release lock
            lock.unlock();
        }
        anyService.doLogging();
        return answer;
    }
}
