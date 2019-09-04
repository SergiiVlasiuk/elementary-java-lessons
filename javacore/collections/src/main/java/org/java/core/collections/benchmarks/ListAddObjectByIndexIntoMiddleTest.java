package org.java.core.collections.benchmarks;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@Slf4j
public class ListAddObjectByIndexIntoMiddleTest extends ListTestParent {
    private void testListAddingObjectInTheMiddle(List list, String title) {
        fillList(list);
        int index = COUNT / 2;
        long startNanoTime = getCurrentNanoTime();
        list.add(index, 3);
        long endNanoTime = getCurrentNanoTime();
        log.info("{} : {} seconds", title, convertToSeconds(endNanoTime - startNanoTime));
    }

    @Override
    public void execute() {
        testListAddingObjectInTheMiddle(new ArrayList<Integer>(), "ArrayList time");
        testListAddingObjectInTheMiddle(new LinkedList<Integer>(), "LinkedList time");
        testListAddingObjectInTheMiddle(new Vector<Integer>(), "Vector time");
    }
}
