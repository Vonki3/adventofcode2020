package nl.vonki3.adventofcode.twentytwenty.day13;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay13 {

    @Test
    void testPart1 () throws IOException {
        final Day13Input input = Day13.read("src/test/resources/input-day-13.txt", Day13.Part.PART1);
        assertEquals(295L, Day13.part1(input));
    }
    @Test
    void testPart2 () throws IOException {
        final Day13Input input = Day13.read("src/test/resources/input-day-13.txt", Day13.Part.PART2);
        assertEquals(1068781L, Day13.part2(input));
    }
}
