package nl.vonki3.adventofcode.twentytwenty.day1;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class TestDay1 {

    @Test
    void test () throws IOException {
        InputReader<Integer> reader = new InputReader<>();
        final List<Integer> input = reader.readInput("src/test/resources/input-day-1.txt", new IntegerInputMapper());

        final Day1.Solution solution1 = Day1.part1(input);
        final Day1.Solution solution2 = Day1.part2(input);

        assertEquals(514579l, solution1.getSolution());
        assertEquals(241861950l, solution2.getSolution());
    }
}
