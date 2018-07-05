package org.java.core.concurency;

public class AnyService<T> {
    public void doSomething() {
        //do some operation, DB read, write etc
    }

    public T doSomething(T toHandle) {
        //do some operation, DB read, write etc
        return toHandle;
    }

    public void doLogging() {
        //logging, no need for thread safety
    }
}
