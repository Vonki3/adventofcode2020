package nl.vonki3.adventofcode.twentytwenty.day10;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.LongInputMapper;

public class TestDay10 {
    @Test
    void testPart1 () throws IOException {
        final InputReader<Long> reader = new InputReader<>();
        List<Long> input = reader.readInput("src/test/resources/input-day-10.txt", new LongInputMapper());
        Assertions.assertEquals(35L, Day10.part1(input));

        input = reader.readInput("src/test/resources/input-day-10-larger.txt", new LongInputMapper());
        Assertions.assertEquals(220L, Day10.part1(input));
    }
    @Test
    void testPart2 () throws IOException {
        final InputReader<Long> reader = new InputReader<>();
        List<Long> input = reader.readInput("src/test/resources/input-day-10.txt", new LongInputMapper());
        System.out.println("solution 1:");
        System.out.println("===========");
        Assertions.assertEquals(8L, Day10.part2(input));

        input = reader.readInput("src/test/resources/input-day-10-larger.txt", new LongInputMapper());
        System.out.println("solution 2:");
        System.out.println("===========");
        Assertions.assertEquals(19208L, Day10.part2(input));
    }
}
