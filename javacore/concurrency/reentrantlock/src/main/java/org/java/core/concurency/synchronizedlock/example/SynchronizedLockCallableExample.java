package org.java.core.concurency.synchronizedlock.example;

import java.util.UUID;
import java.util.concurrent.Callable;

import org.java.core.concurency.AnyService;

public class SynchronizedLockCallableExample implements Callable<UUID> {

    private AnyService<UUID> anyService;

    public SynchronizedLockCallableExample(AnyService<UUID> r) {
        this.anyService = r;
    }

    public void run() {
        synchronized (anyService) {
            anyService.doSomething();
        }
        anyService.doLogging();
    }

    @Override
    public UUID call() throws Exception {
        UUID answer;
        synchronized (anyService) {
            answer = anyService.doSomething(UUID.randomUUID());
        }
        anyService.doLogging();
        return answer;
    }
}
