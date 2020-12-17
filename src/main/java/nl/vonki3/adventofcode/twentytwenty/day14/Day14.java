package nl.vonki3.adventofcode.twentytwenty.day14;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.StringInputMapper;

public class Day14 {

    public static void main(final String[] args) throws IOException {
        List<Day14Input> input = Day14.read("src/main/resources/input-day-14.txt");
        long part1 = Day14.part1(input);

        System.out.println("\nDay 14-1:");
        System.out.println("solution = " + part1);

        input = Day14.read("src/main/resources/input-day-14.txt");
        long part2 = Day14.part2(input);
        System.out.println("\nDay 14-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final List<Day14Input> input) {
        final Memory memory = new Memory();
        for (final Day14Input operation : input) {
            final long memLocation = operation.getOperation().getMemLocation();
            final long addNumber = operation.getOperation().getNumber();
            memory.put(memLocation, mask(operation.getMask(), addNumber));
//            System.out.print("Masking: " + memLocation + ") "+ operation.getMask() + " " + addNumber);
//            System.out.println(" results in: " + memory.get(memLocation));
        }

        return memory.values().stream().mapToLong(nr->nr).sum();
    }


    static long part2(final List<Day14Input> input) {
        final Memory memory = new Memory();
        for (final Day14Input operation : input) {
            final long memLocation = operation.getOperation().getMemLocation();
            final long addNumber = operation.getOperation().getNumber();
            final String maskedValue = maskPart2(operation.getMask(), addNumber);
            memory.put(memLocation, maskedValue);
//            System.out.print("Masking: " + memLocation + ") "+ operation.getMask() + " " + addNumber);
//            System.out.println(" results in: " + memory.get(memLocation));
        }

        return memory.getAdditional()
                .values()
                .stream()
                .mapToLong(l -> l.stream().mapToLong(nr -> nr).sum())
                .sum();
    }

    private static String maskPart2(final String mask, final long addNumber) {
        final String add = addLeadingZeros(Long.toBinaryString(addNumber), mask.length());

        final char[] chars = mask.toCharArray();
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i< chars.length ; i++) {
            char cMask = chars[i];
            char cAdd = add.charAt(i);

            if (cMask == 'X') {
                result.append('X');
                continue;
            }
            if (cMask == '1') {
                result.append('1');
                continue;
            }
            result.append(cAdd);
        }

        return result.toString();
    }


    private static Long mask(final String mask, final long addNumber) {
        final String add = addLeadingZeros(Long.toBinaryString(addNumber), mask.length());

        final char[] chars = mask.toCharArray();
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i< chars.length ; i++) {
            char cMask = chars[i];
            char cAdd = add.charAt(i);

            if (cMask == 'X') {
                result.append(cAdd);
                continue;
            }
            result.append(cMask);
        }

        return Long.parseLong(result.toString(), 2);
    }

    private static String addLeadingZeros(final String source, final int length) {
        final StringBuilder s = new StringBuilder();
        for (int i = 0; i < length-source.length(); i++) {
            s.append("0");
        }
        s.append(source);
        return s.toString();
    }

    static List<Day14Input> read(final String fileName) throws IOException {
        System.out.println("reading");
        System.out.println("==========");
        InputReader<String> reader = new InputReader<>();
        final List<String> input = reader.readInput(fileName, new StringInputMapper());


        System.out.println("\nprocessing");
        System.out.println("==========");

        final List<Day14Input> day14Inputs = new ArrayList<>();
        final Pattern memPattern = Pattern.compile(".*?(\\d+).*?(\\d+)");
        String curMask = "";
        for (String line : input) {
            if (line.startsWith("mask")) {
                curMask = line.substring(7);
                continue;
            }
            if (line.startsWith("mem")) {
                final Matcher matcher = memPattern.matcher(line);
                if (matcher.find()) {
                    day14Inputs.add(
                            new Day14Input(
                                    curMask, new BinaryOperation(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)))
                            )
                    );
                }
            }
        }

        System.out.println(day14Inputs);
        System.out.println("==========");

        return day14Inputs;
    }

}
