package nl.vonki3.adventofcode.twentytwenty.day11;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.day10.Day10;
import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.LongInputMapper;

public class TestDay11 {
    @Test
    void testPart1 () throws IOException {
        final InputReader<SeatRow> reader = new InputReader<>();
        List<SeatRow> input = reader.readInput("src/test/resources/input-day-11.txt", new Day11InputMapper());
        Assertions.assertEquals(37L, Day11.part1(input));
    }
    @Test
    void testPart2 () throws IOException {
        final InputReader<SeatRow> reader = new InputReader<>();
        List<SeatRow> input = reader.readInput("src/test/resources/input-day-11.txt", new Day11InputMapper());
        Assertions.assertEquals(26L, Day11.part2(input));
    }
}
