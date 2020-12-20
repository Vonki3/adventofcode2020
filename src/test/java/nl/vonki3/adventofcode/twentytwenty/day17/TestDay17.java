package nl.vonki3.adventofcode.twentytwenty.day17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.day17.Day17;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay17 {

    @Test
    void testPart1 () throws IOException {
        final PocketDimension input = Day17.read("src/test/resources/input-day-17.txt");
        assertEquals(112L, Day17.part1(input));
    }

    // don't forget to set -Xmx1024M to prevent OOM
    @Test
    void testPart2 () throws IOException {
        final PocketDimension input = Day17.read("src/test/resources/input-day-17.txt");
        assertEquals(0L, Day17.part2(input));
    }

}
