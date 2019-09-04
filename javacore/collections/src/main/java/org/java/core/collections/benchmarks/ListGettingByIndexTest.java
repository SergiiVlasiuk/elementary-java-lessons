package org.java.core.collections.benchmarks;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@Slf4j
public class ListGettingByIndexTest extends ListTestParent {
    public static void main(String[] args) {
        new ListGettingByIndexTest().process();
    }

    private void testListGettingByIndex(List list, String title) {
        fillList(list, COUNT);
        int index = COUNT / 2;
        long startNanoTime = getCurrentNanoTime();
        list.get(index);
        long endNanoTime = getCurrentNanoTime();
        log.info("{} : {} seconds", title, convertToSeconds(endNanoTime - startNanoTime));
    }

    @Override
    public void execute() {
        testListGettingByIndex(new ArrayList<Integer>(), "ArrayList time");
        testListGettingByIndex(new LinkedList<Integer>(), "LinkedList time");
        testListGettingByIndex(new Vector<Integer>(), "Vector time");
    }
}
