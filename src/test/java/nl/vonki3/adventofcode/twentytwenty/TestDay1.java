package nl.vonki3.adventofcode.twentytwenty;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TestDay1 {

    @Test
    public void test () throws IOException {
        final Day1 day = new Day1();
        final List<Integer> input = day.readInput("src/test/resources/input-day-1.txt");

        final Day1.Solution solution1 = day.part1(input);
        final Day1.Solution solution2 = day.part2(input);

        assertEquals(514579l, solution1.getSolution());
        assertEquals(241861950l, solution2.getSolution());
    }
}
