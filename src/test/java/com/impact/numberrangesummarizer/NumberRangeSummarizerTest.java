package com.impact.numberrangesummarizer;

import com.impact.numberrangesummarizer.impl.NumberRangeSummarizerImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ruphin Lobanga
 * E-mail: ruphiny2j@gmail.com
 **/

class NumberRangeSummarizerTest {
    private NumberRangeSummarizer numberRangeSummarizer = new NumberRangeSummarizerImpl();
    String sample = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";

    @Test
    public void testIsCollectionSummarized() {

        String expectedResult = "1,3,6-8,12-15,21-24,31";

        Collection<Integer> numbers = numberRangeSummarizer.collect(sample);

        assertEquals(numberRangeSummarizer.summarizeCollection(numbers), expectedResult);
    }

    @Test
    public void testIsCollectionSequential() {

        Collection<Integer> numbers = numberRangeSummarizer.collect(sample);
        assertFalse(Boolean.parseBoolean(Arrays.stream(numbers.toArray()).unordered().toString()));
    }

    @Test
    public void testCollectionSize() {
        assertEquals(numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31").size(), 14);
    }
}