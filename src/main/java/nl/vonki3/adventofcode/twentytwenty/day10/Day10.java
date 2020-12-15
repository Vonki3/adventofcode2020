package nl.vonki3.adventofcode.twentytwenty.day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.LongInputMapper;

public class Day10 {
    private static final Logger LOG = LoggerFactory.getLogger(Day10.class);
    private final static List<List<Long>> possibleAdapterArrangements = new ArrayList<>();

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
            if (jolt - prevJolt == 1) {
                nr1Jolt++;
            }
            if (jolt - prevJolt == 3) {
                nr3Jolt++;
            }
            prevJolt = jolt;
        }
        nr3Jolt++; // for device: always 3 higher then last adapter;
        return nr1Jolt * nr3Jolt;
    }

    static long part2(final List<Long> input) {
        input.add(0L);
        final List<Long> sortedInput = input.stream().sorted().collect(Collectors.toList());
        sortedInput.add(sortedInput.get(sortedInput.size() - 1) + 3);

        System.out.println("sorted:");
        System.out.println(sortedInput);
        System.out.println("===========");

        possibleAdapterArrangements.add(sortedInput);
        recursive(sortedInput);

//        System.out.println("===========");
        final List<List<Long>> sorted = possibleAdapterArrangements.stream().sorted(Comparator.comparingInt((ToIntFunction<List>) List::size)).collect(Collectors.toList());
        sorted.forEach(System.out::println);
        return possibleAdapterArrangements.size();
    }

    private static int getFirstDeletableAdapterIndex(final List<Long> input, final int startIndex) {
        for (int i = startIndex; i < input.size() - 2; i++) {
            if (input.get(i) + 3 >= input.get(i + 2)) {
                return i + 1;
            }
        }
        return -1;
    }

    private static void recursive(final List<Long> input) {
//        System.out.println("==========");
//        System.out.println(input);
        List<Long> newInput = input;
        int deletableAdapterIndex = getFirstDeletableAdapterIndex(input, 0);
        while (deletableAdapterIndex != -1) {
//            System.out.println("-----------");
//            System.out.println("deleting " + deletableAdapterIndex);
            newInput = new ArrayList<>(newInput);
            newInput.remove(deletableAdapterIndex);
//            if (possibleAdapterArrangements.stream().noneMatch(l -> l.hashCode() == newInput.hashCode())) {
            possibleAdapterArrangements.add(newInput);
            recursive(newInput);
//            }
//            System.out.println(newInput);
            deletableAdapterIndex = getFirstDeletableAdapterIndex(newInput, deletableAdapterIndex - 1);
        }
    }

}
