package nl.vonki3.adventofcode.twentytwenty.day1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class Day1 {
    public static void main(final String[] args) throws IOException {
        InputReader<Integer> reader = new InputReader<>();
        final List<Integer> input = reader.readInput("src/main/resources/input-day-1.txt", new IntegerInputMapper());

        final Solution solution1 = Day1.part1(input);
        final Solution solution2 = Day1.part2(input);

        System.out.println("\nDay 1-1:");
        System.out.println(solution1.toString());
        System.out.println("\nDay 1-2:");
        System.out.println(solution2.toString());

    }

    static Solution part1(final List<Integer> input) {
        final List<Integer> x = new ArrayList<>(input);
        final List<Integer> y = new ArrayList<>(input);
        final Solution solution = new Solution();

        x.forEach(i ->
                y.forEach(j -> {
                    if ((i + j) == 2020) {
                        solution.setSolution(i, j);
                        return;
                    }
                }));
        return solution;
    }

    static Solution part2(final List<Integer> input) {
        final List<Integer> x = new ArrayList<>(input);
        final List<Integer> y = new ArrayList<>(input);
        final List<Integer> z = new ArrayList<>(input);
        final Solution solution = new Solution();

        x.forEach(i ->
                y.forEach(j ->
                        z.forEach(k -> {
                            if ((i + j + k) == 2020) {
                                solution.setSolution(i, j, k);
                                return;
                            }
                        })));
        return solution;
    }

//     static <T> List<T> readInput(final String fileName, MapInputInterface<T> mapper) throws IOException {
//        final List<T> result;
//        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
//            result = lines.map(mapper::map).collect(Collectors.toList());
//        }
//        return result;
//    }

    @Data
    static class Solution {
        private Integer i;
        private Integer j;
        private Integer k;
        private boolean hasSolution = false;

        public void setSolution(final Integer i, final Integer j) {
            this.i = i;
            this.j = j;
            hasSolution = true;
        }

        public void setSolution(final Integer i, final Integer j, final Integer k) {
            this.i = i;
            this.j = j;
            this.k = k;
            hasSolution = true;
        }

        @Override
        public String toString() {
            if (!hasSolution) {
                return "Geen oplossing";
            }

            final StringBuilder sb = new StringBuilder();
            if (i != null) {
                sb.append("i=").append(i).append(" ");
            }
            if (j != null) {
                sb.append("j=").append(j).append(" ");
            }
            if (k != null) {
                sb.append("k=").append(k).append(" ");
            }

            sb.append("solution = ").append(getSolution());

            return sb.toString();
        }

        public long getSolution() {
            long total = 1;
            total *= i != null ? i : 1;
            total *= j != null ? j : 1;
            total *= k != null ? k : 1;

            return total;
        }
    }
}
