package com.jcg.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.Math.abs;

public class ThreadSafeArrayList<E> {
    public static final Random RANDOM = new Random();
    private static final Logger LOGGER = LogManager.getLogger(ThreadSafeArrayList.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private final List<E> list = new ArrayList<>();

    public static void main(String[] args) {
        ThreadSafeArrayList<Integer> threadSafeArrayList = new ThreadSafeArrayList<>();
        IntStream.range(20, 500)
                .parallel()
                .forEach(value -> randomOperation(threadSafeArrayList, value));

        LOGGER.info("Printing the First Element: " + threadSafeArrayList.get(1));
    }

    private static void randomOperation(ThreadSafeArrayList<Integer> threadSafeArrayList, int value) {
        int size = threadSafeArrayList.size();
        if (size == 0) {
            threadSafeArrayList.set(value);
            LOGGER.info("Add to end Element: {}", value);
            return;
        }
        int index = abs(RANDOM.nextInt(size));
        if (RANDOM.nextBoolean()) {
            threadSafeArrayList.set(index, value);
            LOGGER.info("Add to position <{}> Element: {}", index, value);
        } else {
            Integer element = threadSafeArrayList.get(index);
            LOGGER.info("Get from position <{}> Element: {}", index, element);
        }
    }

    public void set(E o) {
        writeLock.lock();
        try {
            list.add(o);
            LOGGER.info("Adding element by thread: " + Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }

    public void set(int index, E o) {
        writeLock.lock();
        try {
            list.add(index, o);
            LOGGER.info("Adding element by thread: " + Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int i) {
        readLock.lock();
        try {
            LOGGER.info("Printing elements by thread: " + Thread.currentThread().getName());
            return list.get(i);
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        readLock.lock();
        try {
            LOGGER.info("reading size: " + Thread.currentThread().getName());
            return list.size();
        } finally {
            readLock.unlock();
        }
    }
}