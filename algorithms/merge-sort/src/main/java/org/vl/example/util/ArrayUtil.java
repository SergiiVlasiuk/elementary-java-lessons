package org.vl.example.util;

import java.util.Arrays;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayUtil {
    private static final Logger LOGGER = LogManager.getLogger(ArrayUtil.class);

    public static int[] generateInputArray() {
        return generateInputArray(30, 200, 80);
    }

    public static int[] generateInputArray(int minValue, int maxValue, int limit) {
        return new Random().ints(minValue, maxValue)
                .limit(limit)
                .toArray();
    }

    public static void copyRange(int[] destinationArray,
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


    public static void printArray(int[] arrayDepthBased) {
        LOGGER.debug(" {}", arrayToString(arrayDepthBased));
    }


    public static String arrayToString(int[] array) {
        return Arrays.toString(Arrays.stream(array).boxed().toArray(Integer[]::new));
    }

}
