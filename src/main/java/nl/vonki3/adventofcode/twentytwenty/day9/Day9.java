package nl.vonki3.adventofcode.twentytwenty.day9;

import java.io.IOException;
import java.util.List;

import javax.naming.NameNotFoundException;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.LongInputMapper;

public class Day9 {
    private static final Logger LOG = LoggerFactory.getLogger(Day9.class);

    public static void main(final String[] args) throws IOException, NameNotFoundException {
        final InputReader<Long> reader = new InputReader<>();
        final List<Long> input = reader.readInput("src/main/resources/input-day-9.txt", new LongInputMapper());

        long part1 = Day9.part1(input, 25, 25);
        long part2 = Day9.part2(input, 25, 25);

        System.out.println("\nDay 9-1:");
        System.out.println("solution = " + part1);
        System.out.println("\nDay 9-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final List<Long> input, final int preamble, final int lookback) {
        long answer = 0;
        boolean found = false;
        for (int cur=preamble; cur<input.size(); cur++) {
            found = false;
            System.out.println("looking at range " + (cur-lookback) + " - " + cur);
            for(int i=cur-lookback; i<cur-1 && !found; i++) {
                for (int j=i+1; j<cur && !found; j++) {
                    final Long nr1 = input.get(i);
                    final Long nr2 = input.get(j);
                    final Long nrCur = input.get(cur);
                    final boolean valid = nr1 + nr2 == nrCur;
                    System.out.println(nr1 + " + " + nr2 + " = " + nrCur + " " + valid);

                    if (valid) {
                        found = true;
                        answer = nrCur;
                        System.out.println("Found it: " + answer);
                    }
                }
            }
            if (!found) {
                return input.get(cur);
            }
        }

        return 0L;
    }

    static long part2(final List<Long> input, final int preamble, final int lookback) {
        final long part1 = part1(input, preamble, lookback);
        System.out.println("================");
        System.out.println("start part2");
        for(int i=0; i<input.size()-1; i++) {
            for (int j=i+1; input.get(j) != part1; j++) {
                final List<Long> subList = input.subList(i, j);
                long sum = sum(subList);
                System.out.println(subList);
                System.out.println(sum + "\n");
                if (sum ==part1) {
                    return answer(subList);
                }
            }
        }
        return 0L;
    }

    private static long answer(final List<Long> subList) {
        long smallest = Long.MAX_VALUE;
        long largest = Long.MIN_VALUE;
        for (Long nr : subList) {
            if (nr < smallest) smallest = nr;
            if (nr > largest) largest = nr;
        }
        return smallest + largest;
    }

    private static long sum(final List<Long> subList) {
        return subList.stream().mapToLong(nr -> nr).sum();
    }
}
