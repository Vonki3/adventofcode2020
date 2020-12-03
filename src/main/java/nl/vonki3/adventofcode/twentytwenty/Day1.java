package nl.vonki3.adventofcode.twentytwenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    public static void main(final String[] args) throws IOException {
        final List<Integer> input = readInput("src/main/resources/input-day-2.txt");
        final Day1 instance = new Day1();
        final Solution solution1 = instance.part1(input);
        final Solution solution2 = instance.part2(input);

        System.out.println("\nDay 1-1:");
        System.out.println(solution1.toString());
        System.out.println("\nDay 1-2:");
        System.out.println(solution2.toString());

    }

    Solution part1(final List<Integer> input) {
        final List<Integer> x = new ArrayList<>(input);
        final List<Integer> y = new ArrayList<>(input);
        final Solution solution = new Solution();


        x.forEach(i -> y.forEach(j -> {
            if ((i + j) == 2020) {
                solution.setSolution(i, j);
                return;
            }
        }));
        return solution;
    }

    Solution part2(final List<Integer> input) throws IOException {
        final List<Integer> x = new ArrayList<>(input);
        final List<Integer> y = new ArrayList<>(input);
        final List<Integer> z = new ArrayList<>(input);
        final Solution solution = new Solution();

        x.forEach(i -> y.forEach(j -> z.forEach(k -> {
            if ((i + j + k) == 2020) {
                solution.setSolution(i, j, k);
                return;
            }
        })));
        return solution;
    }

    static List<Integer> readInput(final String fileName) throws IOException {
        final List<Integer> result;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.map(Integer::valueOf).collect(Collectors.toList());
        }
        return result;
    }

    /**
     *
     */
    class Solution {
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

        public Integer getI() {
            return i;
        }

        public Integer getJ() {
            return j;
        }

        public Integer getK() {
            return k;
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
