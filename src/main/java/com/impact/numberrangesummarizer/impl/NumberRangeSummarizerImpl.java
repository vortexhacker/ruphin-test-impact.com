package com.impact.numberrangesummarizer.impl;

import com.impact.numberrangesummarizer.NumberRangeSummarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Ruphin Lobanga
 * E-mail: ruphiny2j@gmail.com
 **/

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    private int currentNumber = 0;
    private int nextNumber = 0;
    private int indexToIncrement = 0;
    private boolean isSequential;

    @Override
    public Collection<Integer> collect(String input) {
        return Arrays.stream(input.split("[,]"))
                .filter(value -> value.matches("^[0-9]*$"))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> sample) {
        ArrayList<Integer> numbers = new ArrayList<>(sample);
        String str = numbers.get(0).toString();

        return process(numbers, indexToIncrement, str);
    }

    private String process(ArrayList<Integer> numbers, int i, String str){
        currentNumber = (numbers.get(i) + 1);
        nextNumber = numbers.get(i + 1);

        if(currentNumber == nextNumber){
            i++;
            isSequential = true;
        }
        else if(isSequential) {
            str += "-" + numbers.get(i);
            i++;
            str += ("," + numbers.get(i));
            isSequential = false;
        }
        else {
            isSequential = false;
            i++;
            str += "," + numbers.get(i);
        }
        return (i <= numbers.size() - 2 ? process(numbers, i, str) : str) ;
    }
}
