package nl.vonki3.adventofcode.twentytwenty.day12;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.day11.Day11;
import nl.vonki3.adventofcode.twentytwenty.day11.Day11InputMapper;
import nl.vonki3.adventofcode.twentytwenty.day11.SeatRow;
import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class TestDay12 {
    @Test
    void testPart1 () throws IOException {
        final InputReader<Directions> reader = new InputReader<>();
        List<Directions> input = reader.readInput("src/test/resources/input-day-12.txt", new Day12InputMapper());
        Assertions.assertEquals(25L, Day12.part1(input));
    }
    @Test
    void testPart2 () throws IOException {
        final InputReader<Directions> reader = new InputReader<>();
        List<Directions> input = reader.readInput("src/test/resources/input-day-12.txt", new Day12InputMapper());
        Assertions.assertEquals(286L, Day12.part2(input));
    }
}
