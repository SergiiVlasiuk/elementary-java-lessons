package org.java.core.collections.benchmarks;

import org.junit.Test;

public class ListBenchMarkTest {

    @Test
    public void launchAllTests() {
        new ListFillingTest().process();
        new ListGettingByIndexTest().process();
        new ListContainsObjectInTheMiddleTest().process();
        new ListRemovingObjectByIndexFromMiddleTest().process();
        new ListAddObjectByIndexIntoMiddleTest().process();
    }
}