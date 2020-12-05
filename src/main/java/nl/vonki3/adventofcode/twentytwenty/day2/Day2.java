package nl.vonki3.adventofcode.twentytwenty.day2;

import java.io.IOException;
import java.util.List;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class Day2 {

    public static void main(final String[] args) throws IOException {
        InputReader<Day2Input> reader = new InputReader<>();
        final List<Day2Input> input = reader.readInput("src/main/resources/input-day-2.txt", new Day2InputMapper());

        long part1 = Day2.part1(input);
        long part2 = Day2.part2(input);

        System.out.println("\nDay 2-1:");
        System.out.println("solution = " + part1);
        System.out.println("\nDay 2-2:");
        System.out.println("solution = " + part2);
    }

    static public long part1(final List<Day2Input> input) {
        return input.stream()
                .filter(Day2Input::isValidPartOne)
                .count();
    }

    static public long part2(final List<Day2Input> input) {
        return input.stream()
                .filter(Day2Input::isValidPartTwo)
                .count();
    }

}
