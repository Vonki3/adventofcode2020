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
        final List<Integer> input = Day16.read("src/test/resources/input-day-16.txt");
        assertEquals(436L, Day16.part1(input));
    }

    // don't forget to set -Xmx1024M to prevent OOM
    @Test
    void testPart2 () throws IOException {
        final List<Integer> input = Day16.read("src/test/resources/input-day-16.txt");
        assertEquals(175594L, Day16.part2(input));
    }


}
