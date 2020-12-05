package nl.vonki3.adventofcode.twentytwenty.day2;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.day2.Day2;
import nl.vonki3.adventofcode.twentytwenty.day2.Day2Input;
import nl.vonki3.adventofcode.twentytwenty.day2.Day2InputMapper;
import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay2 {

    @Test
    void test () throws IOException {
        InputReader<Day2Input> reader = new InputReader<>();
        final List<Day2Input> input = reader.readInput("src/test/resources/input-day-2.txt", new Day2InputMapper());
        assertEquals(4l, Day2.part1(input));
        assertEquals(1l, Day2.part2(input));
    }
}
