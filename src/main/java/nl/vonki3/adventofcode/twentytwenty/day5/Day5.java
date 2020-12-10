package nl.vonki3.adventofcode.twentytwenty.day5;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.StringInputMapper;

public class Day5 {
    public static void main(final String[] args) throws IOException {
        InputReader<String> reader = new InputReader<>();
        final List<String> input = reader.readInput("src/main/resources/input-day-5.txt", new StringInputMapper());

        System.out.println("\nDay 5-1:");
        System.out.println(Day5.part1(input));
        System.out.println("\nDay 5-2:");
        System.out.println(Day5.part2(input));

    }

    static long part1(final List<String> input) {
        return input.stream()
                .map(Day5::calculate)
                .max(Comparator.naturalOrder())
                .orElse(0l);
    }

    static long part2(final List<String> input) {

        final List<Long> seatIds = input.stream()
                .map(Day5::calculate)
                .sorted()
                .collect(Collectors.toList());
//        seatIds.forEach(System.out::println);

        for (int i=1; i<seatIds.size()-1; i++) {
            final Long prevSeat = seatIds.get(i - 1);
            final Long curSeat = seatIds.get(i);

            if (curSeat - prevSeat >= 2) {
                return curSeat-1;
            }
        }

        throw new IllegalStateException("No empty seat found");
    }

    static long calculate(final String seat) {
        int row = calc(seat.substring(0, 7), CodeType.ROW);
        int column = calc(seat.substring(7), CodeType.COLUMN);

        return row * 8 + column;
    }

    private static int calc(final String code, final CodeType codeType) {
        int min = 0;
        int max = codeType.getMax();
        int half = max / 2;

        final char[] chars = code.toCharArray();
        for (char c : chars) {
            if (codeType.isBottomHalf(c)) {
                max = half;
            } else {
                min = half;
            }
            half = min + (max - min) / 2;
        }
        return half;
    }

    enum CodeType {
        COLUMN(8, 'L'), ROW(128, 'F');

        private final int max;
        private final char bottomHalf;

        CodeType(final int max, final char bottomHalf) {
            this.max = max;
            this.bottomHalf = bottomHalf;
        }

        public int getMax() {
            return max;
        }

        public boolean isBottomHalf(final char c) {
            return c == bottomHalf;
        }
    }
}
