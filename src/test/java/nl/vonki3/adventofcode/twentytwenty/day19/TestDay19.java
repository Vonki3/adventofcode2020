package nl.vonki3.adventofcode.twentytwenty.day19;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay19 {

    @Test
    void testPart1 () throws IOException {
        Day19Input input = Day19.read("src/test/resources/input-day-19.txt");
        assertEquals(2L, Day19.part1(input));
    }

    @Test
    void testPart2 () throws IOException {
        final Day19Input input = Day19.read("src/test/resources/input-day-19-2.txt");
        assertEquals(12L, Day19.part1(input));
    }

}
