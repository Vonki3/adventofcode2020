package nl.vonki3.adventofcode.twentytwenty.day8;

import java.io.IOException;
import java.util.List;

import javax.naming.NameNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class TestDay8 {
    @Test
    void testPart1 () throws IOException {
        final InputReader<GameConsoleInstruction> reader = new InputReader<>();
        final List<GameConsoleInstruction> input = reader.readInput("src/test/resources/input-day-8.txt", new Day8InputMapper());
        Assertions.assertEquals(5L, Day8.part1(input));
    }
    @Test
    void testPart2 () throws IOException {
        final InputReader<GameConsoleInstruction> reader = new InputReader<>();
        final List<GameConsoleInstruction> input = reader.readInput("src/test/resources/input-day-8.txt", new Day8InputMapper());

        Assertions.assertEquals(8L, Day8.part2(input));
    }
}
