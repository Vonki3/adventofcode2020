package nl.vonki3.adventofcode.twentytwenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

public class Day3 {
    Day3Input input;

    public static void main(final String[] args) throws IOException {
        final Day3 instance = new Day3("src/main/resources/input-day-3.txt");
        int part1 = instance.part1(3, 1);

        int[][] slopes = {{1,1},{3,1},{5,1},{7,1},{1,2}};
        long part2 = instance.part2(slopes);

        System.out.println ("\nDay 3-1:");
        System.out.println ("solution = " + part1);
        System.out.println ("\nDay 3-2:");
        System.out.println ("solution = " + part2);
    }

    public Day3(final String fileName) throws IOException {
        input = new Day3Input(fileName);
    }

    int part1(final int slopeX, final int slopeY) throws IOException {
        int nrTrees=0;
        int curRow=0;
        int curColumn=0;

        final List<List<Boolean>> map = input.getMap();
        final int nrRows = map.size();
        final int nrColums = map.get(0).size();

        while (curRow+slopeY< nrRows) {
            if(map.get(curRow + slopeY).get( (curColumn + slopeX) % nrColums)) {
                nrTrees ++;
            }
            curColumn+=slopeX;
            curRow+=slopeY;
        }

        return nrTrees;
    }

    long part2(final int[][] slopes) throws IOException {
        long total = 1;

        for (final int[] slope : slopes) {
            total *= part1(slope[0], slope[1]);
        }

        return total;
    }

    @Data
    static class Day3Input {
        List<List<Boolean>> map;

        Day3Input(final String fileName) throws IOException {
            readInput(fileName);
        }

        private void readInput(final String fileName) throws IOException {
            try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
                map = (lines.map(this::map).collect(Collectors.toList()));
            }
        }

        private List<Boolean> map(final String line) {
            final char[] chars = line.toCharArray();
            List<Boolean> result = new ArrayList<>();
            for (final char aChar : chars) {
                result.add(aChar == '#');
            }
            return result;
//            return Stream.of(line.toCharArray())
//                    .map(c -> c.equals('#'))
//                    .collect(Collectors.toList());
        }

    }
}
