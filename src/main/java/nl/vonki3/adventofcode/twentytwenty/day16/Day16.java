package nl.vonki3.adventofcode.twentytwenty.day16;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.StringInputMapper;

public class Day16 {

    public static void main(final String[] args) throws IOException {
        List<Integer> input = Day16.read("src/main/resources/input-day-16.txt");
        long part1 = Day16.part1(input);

        System.out.println("\nDay 16-1:");
        System.out.println("solution = " + part1);

        input = Day16.read("src/main/resources/input-day-16.txt");
        long part2 = Day16.part2(input);
        System.out.println("\nDay 16-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final List<Integer> input) {
        int turn = input.size();

        while (turn != 2020) {
            final Integer curNr = input.get(turn - 1);
            int prevIndex = getPrevIndex(input, curNr, turn - 1);
            if (prevIndex == -1) {
                input.add(new Integer(0));
            } else {
                final Integer newNr = new Integer((turn - 1 - prevIndex));
                input.add(newNr);
            }
            turn++;
        }

        return input.get(input.size() - 1);
    }

    public static int getPrevIndex(final List<Integer> input, final Integer curNr, final int prevIndex) {
        int index = prevIndex - 1;
        while (index >= 0 && !input.get(index).equals(curNr)) {
            index--;
            // do nothing
        }

        return index < 0 ? -1 : index;
    }


    static long part2(final List<Integer> input) {
        Map<Integer, List<Integer>> nrLastUsed = new HashMap<>();
        input.forEach(nr -> {
            final List<Integer> list = new ArrayList<>();
            list.add(nrLastUsed.size());
            nrLastUsed.put(nr, list);
        });
        System.out.println(nrLastUsed);

        int turn = input.size();

        while (turn != 30000000) {
            if (turn % 100000 == 0) {
                System.out.println((turn + 1) + ") ");
            }
            if (turn < 10) {
                System.out.println("Input:\t\t"+ input);
                System.out.println("nrLastUsed:\t"+ nrLastUsed);
            }

            final int indexOfLastSpokenNumber = input.size()-1;
            final int lastSpokenNumber = input.get(indexOfLastSpokenNumber);
            final List<Integer> previousTurns = nrLastUsed.get(lastSpokenNumber);

            if (isLastSpokenNr(indexOfLastSpokenNumber, previousTurns)){
                input.add(0);
                addNewNr(input, nrLastUsed, turn, 0);
            }
            else {
                final Integer turnOfLastSpokenNr = previousTurns.get(previousTurns.size() - 1);
                final int newNr = previousTurns.size() == 1
                        ? (turn - 1 - turnOfLastSpokenNr)
                        : (turnOfLastSpokenNr - previousTurns.get(previousTurns.size() - 2));
                addNewNr(input, nrLastUsed, turn, newNr);
            }
            turn++;
        }

        System.out.println("Size: " + input.size());
        return input.get(input.size() - 1);
    }

    private static void addNewNr(final List<Integer> input, final Map<Integer, List<Integer>> orderedInput, final int turn, final int newNr) {
        input.add(newNr);
        List<Integer> orderOfNewNr = orderedInput.get(newNr);
        if (orderOfNewNr == null) {
            orderOfNewNr = new ArrayList<>();
            orderedInput.put(newNr, orderOfNewNr);
        } else if (orderOfNewNr.size() == 2) {
            orderOfNewNr.remove(0); // prevents growth of the list, you only need the last 2 references
        }
        orderOfNewNr.add(turn);
    }

    private static boolean isLastSpokenNr(final int sizeOfSpokenNumbers, final List<Integer> previousTurns) {
        final Integer lastIndexOfLastSpokenNumber = previousTurns.get(previousTurns.size() - 1);
        return (sizeOfSpokenNumbers-1) == lastIndexOfLastSpokenNumber;
    }

    static List<Integer> read(final String fileName) throws IOException {
        System.out.println("reading");
        System.out.println("==========");
        InputReader<String> reader = new InputReader<>();
        final List<String> input = reader.readInput(fileName, new StringInputMapper());

        final String[] split = input.get(0).split(",");
        final List<Integer> result = Arrays.stream(split).map(Integer::valueOf).collect(Collectors.toList());

        System.out.println(result);
        System.out.println("==========");

        return result;
    }

}
