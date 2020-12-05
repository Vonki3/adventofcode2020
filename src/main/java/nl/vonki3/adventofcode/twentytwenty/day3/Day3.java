package nl.vonki3.adventofcode.twentytwenty.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;
import nl.vonki3.adventofcode.twentytwenty.day2.Day2Input;
import nl.vonki3.adventofcode.twentytwenty.day2.Day2InputMapper;
import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class Day3 {

    public static void main(final String[] args) throws IOException {
        InputReader<List<Boolean>> reader = new InputReader<>();
        final List<List<Boolean>> input = reader.readInput("src/main/resources/input-day-3.txt", new Day3InputMapper());

        int part1 = Day3.part1(input,3, 1);

        int[][] slopes = {{1,1},{3,1},{5,1},{7,1},{1,2}};
        long part2 = Day3.part2(input, slopes);

        System.out.println ("\nDay 3-1:");
        System.out.println ("solution = " + part1);
        System.out.println ("\nDay 3-2:");
        System.out.println ("solution = " + part2);
    }

    static int part1(final List<List<Boolean>> input, final int slopeX, final int slopeY) {
        int nrTrees=0;
        int curRow=0;
        int curColumn=0;

        final int nrRows = input.size();
        final int nrColums = input.get(0).size();

        while (curRow+slopeY< nrRows) {
            if(input.get(curRow + slopeY).get( (curColumn + slopeX) % nrColums)) {
                nrTrees ++;
            }
            curColumn+=slopeX;
            curRow+=slopeY;
        }

        return nrTrees;
    }

    static long part2(final List<List<Boolean>> input, final int[][] slopes) {
        long total = 1;

        for (final int[] slope : slopes) {
            total *= part1(input, slope[0], slope[1]);
        }

        return total;
    }
}
