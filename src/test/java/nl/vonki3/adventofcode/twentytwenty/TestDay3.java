package nl.vonki3.adventofcode.twentytwenty;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay3 {

    @Test
    public void test () throws IOException {
        final Day3 day = new Day3("src/test/resources/input-day-3.txt");
        assertEquals(7l, day.part1(3, 1));

        int[][] slopes = {{1,1},{3,1},{5,1},{7,1},{1,2}};
        assertEquals(252l, day.part2(slopes));
    }
}
