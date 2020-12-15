package nl.vonki3.adventofcode.twentytwenty.day14;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.day14.Day14;
import nl.vonki3.adventofcode.twentytwenty.day14.Day14Input;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay14 {

    @Test
    void testPart1 () throws IOException {
        final List<Day14Input> input = Day14.read("src/test/resources/input-day-14.txt");
        assertEquals(165L, Day14.part1(input));
    }
    @Test
    void testPart2 () throws IOException {
        final List<Day14Input> input = Day14.read("src/test/resources/input-day-14-2.txt");
        assertEquals(208L, Day14.part2(input));
    }

    @Test
    void testRecursive() {
        Memory m = new Memory();
        final List<String> recursive = new ArrayList<>();

        m.recursive("0XX0X", recursive);
        recursive.forEach(System.out::println);
        assertEquals(8, recursive.size());
    }
    @Test
    void testCalc() {
        Memory m = new Memory();

        final String value = "001X010";
        int indexOfX = value.indexOf("X");
        final List<String> calc = m.calc(value, indexOfX);
        calc.forEach(System.out::println);
        assertEquals(2, calc.size());
    }
}
