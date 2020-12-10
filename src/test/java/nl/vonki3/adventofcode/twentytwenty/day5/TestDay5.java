package nl.vonki3.adventofcode.twentytwenty.day5;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.StringInputMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay5 {

    @Test
    void testPart1 () throws IOException {
        InputReader<String> reader = new InputReader<>();
        final List<String> input = reader.readInput("src/test/resources/input-day-5.txt", new StringInputMapper());
        assertEquals(357L, Day5.calculate(input.get(0)));
        assertEquals(567L, Day5.calculate(input.get(1)));
        assertEquals(119L, Day5.calculate(input.get(2)));
        assertEquals(820L, Day5.calculate(input.get(3)));
    }

    @Test
    void testPart2 () throws IOException {
        InputReader<String> reader = new InputReader<>();
        final List<String> input = reader.readInput("src/main/resources/input-day-5.txt", new StringInputMapper());
        assertEquals(610L, Day5.part2(input));


    }
}
