package nl.vonki3.adventofcode.twentytwenty.day16;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.day16.Day16;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay16 {

    @Test
    void testPart1 () throws IOException {
        final Input input = Day16.read("src/test/resources/input-day-16.txt");
        assertEquals(71L, Day16.part1(input));
    }

    // don't forget to set -Xmx1024M to prevent OOM
    @Test
    void testPart2 () throws IOException {
        final Input input = Day16.read("src/test/resources/input-day-16-2.txt");
        assertEquals(11*12*13L, Day16.part2(input));
    }


}
