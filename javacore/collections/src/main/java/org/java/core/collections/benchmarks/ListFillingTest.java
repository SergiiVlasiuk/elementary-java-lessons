package org.java.core.collections.benchmarks;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@Slf4j
public class ListFillingTest extends ListTestParent {
    private void testListFilling(List list, String title) {
        long startNanoTime = getCurrentNanoTime();
        fillList(list);
        long endNanoTime = getCurrentNanoTime();
        log.info("{} : {} seconds", title, convertToSeconds(endNanoTime - startNanoTime));
    }

    @Override
    public void execute() {
        testListFilling(new ArrayList<Integer>(), "ArrayList time");
        testListFilling(new LinkedList<Integer>(), "LinkedList time");
        testListFilling(new Vector<Integer>(), "Vector time");
    }
}
