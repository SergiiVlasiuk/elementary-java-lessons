package org.java.core.concurency.synchronizedlock.example;

import org.java.core.concurency.AnyService;

public class SynchronizedLockRunnableExample implements Runnable {

    private AnyService anyService;

    public SynchronizedLockRunnableExample(AnyService r) {
        this.anyService = r;
    }

    @Override
    public void run() {
        synchronized (anyService) {
            anyService.doSomething();
        }
        anyService.doLogging();
    }
}
