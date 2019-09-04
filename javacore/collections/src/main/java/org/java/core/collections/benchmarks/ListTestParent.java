package org.java.core.collections.benchmarks;

import java.util.List;

public abstract class ListTestParent {
    static final int COUNT = 1000000;
    static final int NANO = 1000000000;
    static final int TIMES_TO_EXECUTE = 5;

    void fillList(List<Integer> list) {
        for (int i = 0; i < ListTestParent.COUNT; i++) {
            list.add(i);
        }
    }

    long getCurrentNanoTime() {
        return System.nanoTime();
    }

    String convertToSeconds(double nanoTime) {
        return String.valueOf(nanoTime / NANO);
    }

    public void process() {
        System.out.println(String.format("\n\n### %s\n```", getClass().getSimpleName()));
        for (int i = 0; i++ < TIMES_TO_EXECUTE; ) {
            execute();
        }
        System.out.println("```");
    }

    abstract public void execute();
}
