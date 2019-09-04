package org.java.core.collections.benchmarks;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@Slf4j
public class ListRemovingObjectByIndexFromMiddleTest extends ListTestParent {
    public static void main(String[] args) {
        new ListRemovingObjectByIndexFromMiddleTest().process();
    }

    private void testListRemovingFromMiddleByIndex(List list, String title) {
        fillList(list, COUNT);
        int index = COUNT / 2;
        long startNanoTime = getCurrentNanoTime();
        list.remove(index);
        long endNanoTime = getCurrentNanoTime();
        log.info("{} : {} seconds", title, convertToSeconds(endNanoTime - startNanoTime));
    }

    @Override
    public void execute() {
        testListRemovingFromMiddleByIndex(new ArrayList<Integer>(), "ArrayList time");
        testListRemovingFromMiddleByIndex(new LinkedList<Integer>(), "LinkedList time");
        testListRemovingFromMiddleByIndex(new Vector<Integer>(), "Vector time");
    }
}
