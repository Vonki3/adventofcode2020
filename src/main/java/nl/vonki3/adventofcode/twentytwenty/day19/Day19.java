package nl.vonki3.adventofcode.twentytwenty.day19;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.StringInputMapper;

public class Day19 {

    public static void main(final String[] args) throws IOException {
        Day19Input input = Day19.read("src/main/resources/input-day-19.txt");
        long part1 = Day19.part1(input);

        System.out.println("\nDay 19-1:");
        System.out.println("solution = " + part1); // should be 285, regex is good, but with input it comes up with result 0

        input = Day19.read("src/main/resources/input-day-19-2.txt");
        long part2 = Day19.part1(input);
        System.out.println("\nDay 19-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final Day19Input input) {
        final Pattern pattern = Pattern.compile(input.rules.get(0));
        System.out.println(input.rules.get(0));
//        return input.messages.stream()
//                .filter(message -> pattern.matcher(message).matches())
//                .count();
        int nrMatches = 0;
        for (final String message : input.messages) {
            Matcher matcher = pattern.matcher(message);
            if (matcher.matches()) {
                nrMatches++;
                System.out.println("Matched: " + message + " -> " + matcher.group());
            }
            else {
                System.out.println("NO match: " + message);
            }
        }

        return nrMatches;
    }

    static long part2(final Day19Input input) {
        return 0L;
    }


    static Day19Input read(final String fileName) throws IOException {
        System.out.println("reading");
        System.out.println("==========");

        final InputReader<String> reader = new InputReader<>();
        final Day19Input result = new Day19Input(reader.readInput(fileName, new StringInputMapper()));


        System.out.println(result);
        System.out.println("==========");

        return result;
    }
}
