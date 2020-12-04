package nl.vonki3.adventofcode.twentytwenty;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay4 {

    @Test
    void testDefaultTestInput () throws IOException {
        final Day4 day = new Day4("src/test/resources/input-day-4.txt");
        assertEquals(2l, day.part1());
        assertEquals(2l, day.part2());
    }

    @Test
    void testInputWithInvalidValues () throws IOException {
        final Day4 day = new Day4("src/test/resources/input-day-4-invalids.txt");
        assertEquals(8l, day.part1());
        assertEquals(4l, day.part2());
    }
}
