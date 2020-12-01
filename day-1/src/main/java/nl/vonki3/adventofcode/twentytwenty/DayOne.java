package nl.vonki3.adventofcode.twentytwenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.exit;

public class DayOne {
    public static void main(final String[] args) throws IOException {
        DayOne instance = new DayOne();
        instance.part1();
        instance.part2();
    }

    private void part1() throws IOException {
        final List<Integer> x = readInput("day-1/src/main/resources/input-day-one.txt");
        final List<Integer> y = new ArrayList<>(x);
        final Solution solution = new Solution();


        x.forEach(i -> y.forEach(j -> {
            if ((i + j) == 2020) {
                solution.setSolution(i, j);
                return;
            }
        }));
        System.out.println(solution.toString());
    }

    private List<Integer> readInput(final String fileName) throws IOException {

        final List<Integer> result;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.map(Integer::valueOf).collect(Collectors.toList());
        }
        return result;
    }

    private void part2() throws IOException {
        final List<Integer> x = readInput("day-1/src/main/resources/input-day-one.txt");
        final List<Integer> y = new ArrayList<>(x);
        final List<Integer> z = new ArrayList<>(x);
        final Solution solution = new Solution();

        x.forEach(i -> y.forEach(j -> z.forEach(k -> {
            if ((i + j + k) == 2020) {
                solution.setSolution(i, j, k);
                return;
            }
        })));
        System.out.println(solution.toString());
    }

    private class Solution {
        private Integer i;
        private Integer j;
        private Integer k;
        private boolean hasSolution = false;

        public void setSolution (final Integer i, final Integer j) {
            this.i = i;
            this.j = j;
            hasSolution = true;
        }

        public void setSolution (final Integer i, final Integer j, final Integer k) {
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
            int total = 1;

            if (i != null) { sb.append("i=").append(i).append(" "); total *= i; }
            if (j != null) { sb.append("j=").append(j).append(" "); total *= j; }
            if (k != null) { sb.append("k=").append(k).append(" "); total *= k; }

            sb.append( "\nsolution = ").append(total).append ("\n");

            return sb.toString();
        }
    }
}
