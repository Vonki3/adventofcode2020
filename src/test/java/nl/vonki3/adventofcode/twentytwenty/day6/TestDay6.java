package nl.vonki3.adventofcode.twentytwenty.day6;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay6 {

    @Test
    void testPart1 () throws IOException, InstantiationException, IllegalAccessException {
        assertEquals(36L, Day6.part1("src/test/resources/input-day-6.txt")); // 11
//        Set<Character> answers1 = new TreeSet<>();
//        answers1.addAll("hdupytrgcwsm".chars().filter(c -> c >= 'a' && c <= 'z').mapToObj(e -> (char) e).collect(Collectors.toSet()));
//        answers1.forEach(System.out::println);
//        System.out.println(answers1.size());
//        answers1.addAll("upwmsringajckob".chars().filter(c -> c >= 'a' && c <= 'z').mapToObj(e -> (char) e).collect(Collectors.toSet()));
//        answers1.forEach(System.out::println);
//        System.out.println(answers1.size());
//        answers1.addAll("wsupgfezmvrdc".chars().filter(c -> c >= 'a' && c <= 'z').mapToObj(e -> (char) e).collect(Collectors.toSet()));
//        answers1.forEach(System.out::println);
//        System.out.println(answers1.size());
//        answers1.addAll("utlpfrswhmcg".chars().filter(c -> c >= 'a' && c <= 'z').mapToObj(e -> (char) e).collect(Collectors.toSet()));
//        answers1.forEach(System.out::println);
//        System.out.println(answers1.size());
//        answers1.addAll("gumyphqswcr".chars().filter(c -> c >= 'a' && c <= 'z').mapToObj(e -> (char) e).collect(Collectors.toSet()));
//        answers1.forEach(System.out::println);
//        System.out.println(answers1.size());
    }
    @Test
    void testPart2 () throws IOException, IllegalAccessException, InstantiationException {
        assertEquals(6L, Day6.part2("src/test/resources/input-day-6.txt"));
    }
}
