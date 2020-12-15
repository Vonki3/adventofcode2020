package nl.vonki3.adventofcode.twentytwenty.day13;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.StringInputMapper;

public class Day13 {

    public static void main(final String[] args) throws IOException {
        Day13Input input = Day13.read("src/main/resources/input-day-13.txt", Part.PART1);
        long part1 = Day13.part1(input);

        System.out.println("\nDay 13-1:");
        System.out.println("solution = " + part1);

        input = Day13.read("src/main/resources/input-day-13.txt", Part.PART2);
        long part2 = Day13.part2(input);
        System.out.println("\nDay 13-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final Day13Input input) {
        System.out.println("input: "+ input.getEta() + " " + input.getBusIds());

        final Day13Answer answer = new Day13Answer(Long.MAX_VALUE, 0L);

        final int rootBus = input.getBusIds().get(0);
        final boolean found=false;
//        while (!found) {
//
//        }
//        id -> {
//            int calcTime = 0;
//
//            while (calcTime < input.getEta()) {
//                calcTime += id;
//            }
//            System.out.println("calc: "+ calcTime);
//
//            final long waitTime = calcTime - input.getEta();
//            if (waitTime < answer.getWaitTime()) {
//                answer.setWaitTime(waitTime);
//                answer.setBusid(id);
//            }
//
//            System.out.println("Best = " + answer.getWaitTime() + ", " + answer.getBusid());
//        });

        return 0L; //answer.getWaitTime() * answer.getBusid();
    }

    static long part2(final Day13Input input) {
        System.out.println("input: "+ input.getEta() + " " + input.getBusIds());

        return 0L;
    }

    static Day13Input read(final String fileName, final Part part) throws IOException {
        InputReader<String> reader = new InputReader<>();
        final List<String> input = reader.readInput(fileName, new StringInputMapper());

        final String eta = input.get(0);

        final String busIds = input.get(1);
        final List<String> busIdsAsList = Arrays.asList(busIds.split(","));

        if (part==Part.PART1) {
            return new Day13Input(
                    Integer.parseInt(eta),
                    busIdsAsList.stream()
                            .filter(id -> !id.equals("x"))
                            .map(Integer::valueOf)
                            .collect(Collectors.toList())
            );
        }
        else {
            return new Day13Input(
                    Integer.parseInt(eta),
                    busIdsAsList.stream()
                            .map(id -> id.equals("x") ? Integer.MAX_VALUE : Integer.parseInt(id))
                            .collect(Collectors.toList())
            );

        }
    }

    enum Part {
        PART1, PART2
    }

}
