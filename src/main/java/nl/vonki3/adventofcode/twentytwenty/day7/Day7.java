package nl.vonki3.adventofcode.twentytwenty.day7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NameNotFoundException;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class Day7 {
    private static final Logger LOG = LoggerFactory.getLogger(Day7.class);

    public static void main(final String[] args) throws IOException, NameNotFoundException {
        final InputReader<Bag> reader = new InputReader<>();
        final List<Bag> input = reader.readInput("src/main/resources/input-day-7.txt", new Day7InputMapper());

        long part1 = Day7.part1(input, "shiny gold");
        long part2 = Day7.part2(input, "shiny gold");

        System.out.println("\nDay 7-1:");
        System.out.println("solution = " + part1);
        System.out.println("\nDay 7-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final List<Bag> input, final String goalBagName) throws NameNotFoundException {
        final Bag goalBag = getBag(input, goalBagName);
        final List<Bag> result = traversePart1(input, goalBag);

        result.forEach(System.out::println);
        return result.size();
    }

    private static List<Bag> traversePart1(final List<Bag> input, final Bag goalBag) {
        final List<Bag> collect = input.stream()
                .filter(bag -> !bag.isTraversed() && bag.getContent() != null && bag.getContent().keySet().stream().anyMatch(b -> b.equals(goalBag)))
                .collect(Collectors.toList());

        if (collect.isEmpty()) {
            return Collections.emptyList();
        }

        collect.forEach(Bag::setTraversed);

        final List<Bag> result = new ArrayList<>(collect);
        result.forEach(System.out::println);
        collect.forEach(bag -> {
            result.addAll(traversePart1(input, bag));
        });

        return result.stream().distinct().collect(Collectors.toList());
    }

    static long part2(final List<Bag> input, final String goalBagName) throws NameNotFoundException {
        final Bag goalBag = getBag(input, goalBagName);
        final List<Bag> result = traversePart2(input, goalBag);
        result.forEach(System.out::println);

        System.out.println("==> "+ goalBag);
        return countPart2(goalBag, result)-1;
    }

    private static long countPart2(final Bag mainBag, final List<Bag> input) throws NameNotFoundException {
        if (mainBag.getContent() == null) {
            System.out.println(mainBag.getName() + "-EOR returning 1");
            return 1;
        }

        final Long[] subTotal = {0L};
        mainBag.getContent().forEach( (subBag, nr) -> {
            try {
                final Bag bag1 = getBag(input, subBag.getName());
                System.out.println("==> "+ bag1);
                subTotal[0] += nr * countPart2(bag1, input);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        });

        System.out.println( mainBag.getName() + ") returning " + subTotal[0]);
        return 1+subTotal[0];
    }

    private static List<Bag> traversePart2(final List<Bag> input, final Bag goalBag) {
        final List<Bag> collect = input.stream().filter(bag -> bag.getName().equals(goalBag.getName())).collect(Collectors.toList());
        if (collect.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Bag> result = new ArrayList<>(collect);
        collect.forEach(bag -> {
            if (bag.getContent()!=null) {
                bag.getContent().keySet().forEach(nextBag -> {
                    result.addAll(traversePart2(input, nextBag));
                });
            }
        });

        return result;

    }

    private static Bag getBag(final List<Bag> input, final String goalBagName) throws NameNotFoundException {
        return input.stream()
                .filter(bag -> bag.getName().equals(goalBagName))
                .findFirst()
                .orElseThrow(NameNotFoundException::new);
    }
}
