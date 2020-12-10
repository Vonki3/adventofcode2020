package nl.vonki3.adventofcode.twentytwenty.day9;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.LongInputMapper;

public class TestDay9 {
    @Test
    void testPart1 () throws IOException {
        final InputReader<Long> reader = new InputReader<>();
        final List<Long> input = reader.readInput("src/test/resources/input-day-9.txt", new LongInputMapper());
        Assertions.assertEquals(127L, Day9.part1(input, 5, 5));
    }
    @Test
    void testPart2 () throws IOException {
        final InputReader<Long> reader = new InputReader<>();
        final List<Long> input = reader.readInput("src/test/resources/input-day-9.txt", new LongInputMapper());

        Assertions.assertEquals(62L, Day9.part2(input, 5, 5));
    }
}
