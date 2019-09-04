package org.java.core.collections.benchmarks;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@Slf4j
public class ListContainsObjectInTheMiddleTest extends ListTestParent {
    private void testListContainsObjectInTheMiddle(List list, String title) {
        fillList(list);
        int number = COUNT / 2;
        long startNanoTime = getCurrentNanoTime();
        list.contains(number);
        long endNanoTime = getCurrentNanoTime();
        log.info("{} : {} seconds", title, convertToSeconds(endNanoTime - startNanoTime));
    }

    @Override
    public void execute() {
        testListContainsObjectInTheMiddle(new ArrayList<Integer>(), "ArrayList time");
        testListContainsObjectInTheMiddle(new LinkedList<Integer>(), "LinkedList time");
        testListContainsObjectInTheMiddle(new Vector<Integer>(), "Vector time");
    }
}
