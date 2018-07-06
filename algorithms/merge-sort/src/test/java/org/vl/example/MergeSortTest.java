package org.vl.example;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.vl.example.util.ArrayUtil.generateInputArray;

@RunWith(MockitoJUnitRunner.class)
public class MergeSortTest {

    @InjectMocks
    private MergeSort testee;

    @Test
    public void sort() {
        int[] randomArray = generateInputArray();
        int[] expected = Arrays.copyOf(randomArray, randomArray.length);
        Arrays.sort(expected);
        assertThat(randomArray).isNotEqualTo(expected);

        testee.sort(randomArray);
        int[] actual = randomArray;

        assertThat(actual).isEqualTo(expected);

    }

}