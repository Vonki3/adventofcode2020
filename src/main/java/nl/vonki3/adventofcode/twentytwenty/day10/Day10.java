package nl.vonki3.adventofcode.twentytwenty.day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.LongInputMapper;

public class Day10 {
    private static final Logger LOG = LoggerFactory.getLogger(Day10.class);

    public static void main(final String[] args) throws IOException {
        final InputReader<Long> reader = new InputReader<>();
        final List<Long> input = reader.readInput("src/main/resources/input-day-10.txt", new LongInputMapper());

        long part1 = Day10.part1(input);
        long part2 = Day10.part2(input);

        System.out.println("\nDay 10-1:");
        System.out.println("solution = " + part1);
        System.out.println("\nDay 10-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final List<Long> input) {
        int nr1Jolt = 0;
        int nr3Jolt = 0;
        long prevJolt = 0;
        final List<Long> sortedInput = input.stream().sorted().collect(Collectors.toList());
        for (Long jolt : sortedInput) {
            if (jolt-prevJolt==1) nr1Jolt ++;
            if (jolt-prevJolt==3) nr3Jolt ++;
            prevJolt = jolt;
        }
        nr3Jolt ++; // for device: always 3 higher then last adapter;
        return nr1Jolt * nr3Jolt;
    }

    static long part2(final List<Long> input) {
        input.add(0L);
        final List<Long> sortedInput = input.stream().sorted().collect(Collectors.toList());
        sortedInput.add(sortedInput.get(sortedInput.size()-1)+3);

        System.out.println("sorted:");
        System.out.println(sortedInput);
        System.out.println("===========");

        final List<List<Long>> possibleAdapterArrangements = new ArrayList<>();
        possibleAdapterArrangements.add(sortedInput);
        recursive(sortedInput, possibleAdapterArrangements, 0);

        return possibleAdapterArrangements.size();
    }

    private static List<Long> getDeletableAdapters(final List<Long> sortedInput) {
        final List<Long> deletableAdapters = new ArrayList<>();

        for (int i = 0; i < sortedInput.size()-2; i++) {
            if (sortedInput.get(i)+3>= sortedInput.get(i+2)) {
                deletableAdapters.add(sortedInput.get(i+1));
            }
        }

        return deletableAdapters;
    }

    private static void recursive(final List<Long> sortedInput, final List<List<Long>> possibleAdapterArrangements, final int ptr) {
        System.out.println("ptr: " + ptr);
        final List<Long> deletableAdapters = getDeletableAdapters(sortedInput);

        deletableAdapters.forEach(jolt -> {
            final List<Long> newInput = new ArrayList<>(sortedInput);
            newInput.remove(sortedInput.indexOf(jolt));
            possibleAdapterArrangements.add(newInput);
        });

//        for(int i = ptr; i < sortedInput.size()-2; i++) {
//            if (sortedInput.get(i)+3>=sortedInput.get(i+2)) {
//                final List<Long> newInput = new ArrayList<>(sortedInput);
//                newInput.remove(i+1);
//                possibleAdapterArrangements.add(newInput);
//            }
//        }
        System.out.println("nr answers: " + possibleAdapterArrangements.size());
        if (ptr < sortedInput.size()-3) {
            possibleAdapterArrangements.forEach(input -> recursive(input, possibleAdapterArrangements, ptr + 1));
        }
    }

}
