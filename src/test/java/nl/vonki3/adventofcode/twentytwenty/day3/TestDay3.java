package nl.vonki3.adventofcode.twentytwenty.day3;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.day3.Day3;
import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay3 {

    @Test
    void test () throws IOException {
        InputReader<List<Boolean>> reader = new InputReader<>();
        final List<List<Boolean>> input = reader.readInput("src/test/resources/input-day-3.txt", new Day3InputMapper());
        assertEquals(7l, Day3.part1(input, 3, 1));

        int[][] slopes = {{1,1},{3,1},{5,1},{7,1},{1,2}};
        assertEquals(252l, Day3.part2(input, slopes));
    }
}
