package nl.vonki3.adventofcode.twentytwenty.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {
    public static void main(final String[] args) throws IOException, IllegalAccessException, InstantiationException {
        System.out.println("\nDay 6-1:");
        System.out.println(Day6.part1("src/main/resources/input-day-6.txt"));
        System.out.println("\nDay 6-2:");
        System.out.println(Day6.part2("src/main/resources/input-day-6.txt"));

    }

    static long part1(final String fileName) throws FileNotFoundException, InstantiationException, IllegalAccessException {
        Class<PassengerSet> groupClazz = PassengerSet.class;
        final List<Group> groupList = Day6.read(fileName, groupClazz);
        groupList.stream().forEach(group -> group.stream().map(answers -> answers).forEach(System.out::println));
//        groupList.forEach(group -> {
//            group.forEach(answers -> {
//                answers.size();
//            });
//        });
//        final List<List<Stream<PassengerAnswers>>> collect1 = groupList.stream()
//                .map(groupAnswer -> groupAnswer.stream()
//                        .map(Stream::of)
//                        .collect(Collectors.toList())
//                )
//                .collect(Collectors.toList());


//                .mapToInt(passengerAnswers -> passengerAnswers.stream()
//                        .map(passengerAnswers -> passengerAnswers
//                                .distinct()
//                                .collect(Collectors.toList()))
//                        .mapToInt(Collection::size)
//                        .sum())
//                .sum();
        return 0l;
    }

    static long part2(final String fileName) throws FileNotFoundException, InstantiationException, IllegalAccessException {
        final Class<PassengerList> groupClazz = PassengerList.class;
        final List<Group> passengerGroups = Day6.read(fileName, groupClazz);
        return passengerGroups.stream()
                .filter(groupAnswers -> !groupAnswers.stream()
                        .filter(passengerAnswer -> groupAnswers.stream()
                                .allMatch(curAnswer -> curAnswer.equals(passengerAnswer))).collect(Collectors.toList()).isEmpty())
                .mapToInt(Collection::size)
                .sum();
    }

    static List<Group> read(final String fileName, final Class<? extends PassengerAnswers> passengerAnswersClazz) throws FileNotFoundException, IllegalAccessException, InstantiationException {
        final List<Group> allAnswers = new ArrayList<>();
        Group groupAnswers = new Group();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.useDelimiter("\\r\\n");
            while (scanner.hasNext()) {
                final String next = scanner.next();
                if (next.equals("")) {
                    // found a new passengergroup, store the current and create a new passengergroup
                    allAnswers.add(groupAnswers);
                    groupAnswers = new Group();
                } else {
                    final PassengerAnswers passengerAnswers = passengerAnswersClazz.newInstance();
                    passengerAnswers.addAll(next.chars().filter(c -> c >= 'a' && c <= 'z').mapToObj(e -> (char) e).collect(Collectors.toSet()));
                    groupAnswers.add(passengerAnswers);
                }
                if (!scanner.hasNext()) {
                    allAnswers.add(groupAnswers);
                }
            }
        }

        return allAnswers;
    }

}
