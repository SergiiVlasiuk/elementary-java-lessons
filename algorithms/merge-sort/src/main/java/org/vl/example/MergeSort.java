package org.vl.example;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MergeSort {
    private static final Logger LOGGER = LogManager.getLogger(MergeSort.class);

    private int[] inputArray;
    private int[] tempMergeArray;
    private int length;

    public static void main(String[] args) {

        int[] inputArray = generateInputArray();

        MergeSort ms = new MergeSort();
        ms.printArray(inputArray);
        ms.sort(inputArray);

    }

    private static int calculateDepth(int x, int base) {
        return (int) Math.ceil((Math.log(x) / Math.log(base)));
    }

    public void sort(int[] inputArray) {
        this.inputArray = inputArray;
        this.length = inputArray.length;
        this.tempMergeArray = new int[length];

        int depth = calculateDepth(length, 2);
        LOGGER.debug("Input tree is: {}", arrayToString(this.inputArray));
        LOGGER.debug("The tree length is: <{}>, the depth is: <{}>.", length, depth);
        for (int i = 0; i < depth; i++) {
            LOGGER.debug("{} ", i);
            int shift = (int) Math.pow(2, i);
            for (int j = 0; j < length; j = j + shift * 2) {
//                printArray(getArrayDepthBased(this.inputArray, j, j + shift));
//                printArray(getArrayDepthBased(this.inputArray, j + shift, j + 2 * shift));
                mergeArraysDepthBased(shift, j, j + shift);
            }
        }
    }

    private void printArray(int[] arrayDepthBased) {
        LOGGER.debug(" {}", arrayToString(arrayDepthBased));
    }

    private static String arrayToString(int[] array) {
        return Arrays.toString(Arrays.stream(array).boxed().toArray(Integer[]::new));
    }

    /*
     Method to check temporary result. It`s helpful to detect collection part that is under merging.
     */
    private int[] getArrayDepthBased(int[] inputArr,
                                     int start,
                                     int totalShift) {
        int[] result = new int[totalShift - start];
        if (totalShift > inputArr.length) {
            totalShift = inputArr.length;
        }
        for (int i = start; i < totalShift; i++) {
            if (totalShift > inputArr.length) {
                break;
            }
            result[i - start] = inputArr[i];
        }
        return result;
    }

    private void mergeArraysDepthBased(int fazeLength,
                                       int startShiftFirstArray,
                                       int startShiftSecondArray) {
        int i = startShiftFirstArray;
        int k = startShiftFirstArray;
        int j = startShiftSecondArray;
        int maxShiftFirstArray = startShiftFirstArray + fazeLength;
        int maxShiftSecondArray = startShiftSecondArray + fazeLength;
        if (this.length - 1 < maxShiftSecondArray) {
            maxShiftSecondArray = this.length;
        }
        if (this.length - 1 < maxShiftFirstArray) {
            maxShiftFirstArray = this.length;
        }

        while (i <= maxShiftFirstArray && j <= maxShiftSecondArray) {
            try {
                if (j == maxShiftSecondArray) {
                    copyRange(tempMergeArray, inputArray, i, maxShiftFirstArray, k);
                    break;
                } else if (i == maxShiftFirstArray) {
                    copyRange(tempMergeArray, inputArray, j, maxShiftSecondArray, k);
                    break;
                }
                if (inputArray[i] < inputArray[j]) {
                    tempMergeArray[k] = inputArray[i];
                    i++;
                } else {
                    tempMergeArray[k] = inputArray[j];
                    j++;
                }
                k++;
            } catch (ArrayIndexOutOfBoundsException e) {
                LOGGER.error(e);
            }
        }
        copyRange(inputArray, tempMergeArray, startShiftFirstArray, maxShiftSecondArray, startShiftFirstArray);

        printArray(inputArray);
    }

    private void copyRange(int[] destinationArray,
                           int[] sourceArray,
                           int index,
                           final int maxShiftArray,
                           int startDestinationIndex) {
        while (index < maxShiftArray) {
            destinationArray[startDestinationIndex] = sourceArray[index];
            index++;
            startDestinationIndex++;
        }
    }

    public static int[] generateInputArray() {
        java.util.Random r = new java.util.Random();
        int min = 10;
        int max = r.nextInt(20) + min;
        return r.ints(0, max).limit(max).toArray();
    }
}
