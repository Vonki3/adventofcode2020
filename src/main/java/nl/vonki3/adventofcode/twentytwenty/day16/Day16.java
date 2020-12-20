package nl.vonki3.adventofcode.twentytwenty.day16;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.StringInputMapper;

public class Day16 {

    public static void main(final String[] args) throws IOException {
        Input input = Day16.read("src/main/resources/input-day-16.txt");
        long part1 = Day16.part1(input);

        System.out.println("\nDay 16-1:");
        System.out.println("solution = " + part1);

        input = Day16.read("src/main/resources/input-day-16.txt");
        long part2 = Day16.part2(input);
        System.out.println("\nDay 16-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final Input input) {
        final List<Integer> validValues = new ArrayList<>();
        input.getKlasses().stream().map(k -> validValues.addAll(k.getValidNumbers())).collect(Collectors.toList());
//        System.out.println(validValues);

        final List<Integer> invalidValues = new ArrayList<>();
        input.getNearbyTickets().forEach(ticket -> {
            invalidValues.addAll(ticket.stream().map(t -> t.intValue())
                    .filter(nr -> validValues.stream().noneMatch(klasseNr -> klasseNr.equals(nr)))
                    .collect(Collectors.toList()));
        });

//        System.out.println(invalidValues);
        return invalidValues.stream().mapToLong(id -> id).sum();
    }


    static long part2(final Input input) {
        final Set<Integer> validValues = new TreeSet<>();
        input.getKlasses().stream().map(k -> validValues.addAll(k.getValidNumbers())).collect(Collectors.toList());
        System.out.println(validValues);

        final List<Ticket> validTickets = new ArrayList<>();
        input.getNearbyTickets().forEach(ticket -> {
            if (ticket.stream()
                    .allMatch(nr -> validValues.stream()
                            .anyMatch(klasseNr -> klasseNr.equals(nr))
                    )
            ) {
                validTickets.add(ticket);
            }
        });

        System.out.println(validTickets);

//        validTickets.forEach(ticket -> {
//            ticket.forEach(nr -> {
//                input.getKlasses().
//            });
//        });
//        return invalidValues.stream().mapToLong(id -> id).sum();
        return 0L;
    }

    static Input read(final String fileName) throws IOException {
        System.out.println("reading");
        System.out.println("==========");
        InputReader<String> reader = new InputReader<>();
        final List<String> inputValues = reader.readInput(fileName, new StringInputMapper());
        final Input input = new Input();
        boolean nextKlasses = false;
        boolean nextNearbyTickets = false;

        for (final String line : inputValues) {
            if (!nextKlasses && !nextNearbyTickets && !"your ticket:".equals(line)) {
                input.getKlasses().add(new Klasse(line));
                continue;
            }
            if ("your ticket:".equals(line)) {
                nextKlasses = true;
                nextNearbyTickets = false;
                continue;
            }
            if ("nearby tickets:".equals(line)) {
                nextKlasses = false;
                nextNearbyTickets = true;
                continue;
            }

            if (nextKlasses) {
                input.getMyTicket().addAll(
                        Arrays.stream(line.split(","))
                                .filter(StringUtils::isNotBlank)
                                .map(Integer::valueOf)
                                .collect(Collectors.toList()));
                continue;
            }

            if (nextNearbyTickets) {
                final Ticket ticket = new Ticket();
                ticket.addAll(
                        Arrays.stream(line.split(","))
                                .filter(StringUtils::isNotBlank)
                                .map(Integer::valueOf)
                                .collect(Collectors.toList()));
                input.getNearbyTickets().add(ticket);
                continue;
            }
        }

//        System.out.println("input = " + input);
//        System.out.println("==========");

        return input;
    }

}
