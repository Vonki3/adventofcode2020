package nl.vonki3.adventofcode.twentytwenty.day4;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay4 {

    @Test
    void testDefaultTestInput () throws IOException {
        final List<Day4.Passport> input = Day4.read("src/test/resources/input-day-4.txt");
        assertEquals(2L, Day4.part1(input));
        assertEquals(2L, Day4.part2(input));
    }

    @Test
    void testInputWithInvalidValues () throws IOException {
        final List<Day4.Passport> input = Day4.read("src/test/resources/input-day-4-invalids.txt");
        assertEquals(8L, Day4.part1(input));
        assertEquals(4L, Day4.part2(input));
    }
}
