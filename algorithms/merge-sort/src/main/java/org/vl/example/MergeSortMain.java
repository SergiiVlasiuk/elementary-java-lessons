package org.vl.example;

import static org.vl.example.util.ArrayUtil.generateInputArray;
import static org.vl.example.util.ArrayUtil.printArray;

public class MergeSortMain {

    public static void main(String[] args) {
        int[] inputArray = generateInputArray();

        MergeSort ms = new MergeSort();
        printArray(inputArray);
        ms.sort(inputArray);
    }

}
