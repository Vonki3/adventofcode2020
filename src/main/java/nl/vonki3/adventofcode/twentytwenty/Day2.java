package nl.vonki3.adventofcode.twentytwenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Day2 {

    public static void main(final String[] args) throws IOException {
        final List<Day2Input> input = readInput("src/main/resources/input-day-2.txt");
        final Day2 instance = new Day2();
        long part1 = instance.part1(input);
        long part2 = instance.part2(input);

        System.out.println ("\nDay 2-1:");
        System.out.println ("solution = " + part1);
        System.out.println ("\nDay 2-2:");
        System.out.println ("solution = " + part2);
    }

    long part1(final List<Day2Input> input) throws IOException {
        return input.stream()
                .filter(Day2Input::isValidPartOne)
                .count();
    }

    long part2(final List<Day2Input> input) throws IOException {
        return input.stream()
                .filter(Day2Input::isValidPartTwo)
                .count();
    }

    static List<Day2Input> readInput(final String fileName) throws IOException {
        List<Day2Input> result = null;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.map(Day2::map).collect(Collectors.toList());
        }
        return result;
    }

    private static Day2Input map(final String line) {
        final String[] elements = line.split(" ");
        final String[] nrTimes = elements[0].split("-");
        final char aChar = elements[1].charAt(0);
        final String password = elements[2];

        return new Day2Input(Integer.parseInt(nrTimes[0]), Integer.parseInt(nrTimes[1]), aChar, password);
    }

    @Data
    @AllArgsConstructor
    static class Day2Input {
        int min;
        int max;
        char aChar;
        String password;

        public boolean isValidPartOne() {
            final int nrTimes = StringUtils.countMatches(password, aChar);
            return nrTimes >= min && nrTimes <= max;
        }

        public boolean isValidPartTwo() {
            boolean match1=password.charAt(min-1)==aChar;
            boolean match2=password.charAt(max-1)==aChar;
            return match1 ^ match2;
        }
    }
}
