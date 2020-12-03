package nl.vonki3.adventofcode.twentytwenty;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay2 {

    @Test
    public void test () throws IOException {
        final Day2 day = new Day2();
        final List<Day2.Day2Input> input = day.readInput("src/test/resources/input-day-2.txt");
        assertEquals(4l, day.part1(input));
        assertEquals(1l, day.part2(input));
    }
}
