package nl.vonki3.adventofcode.twentytwenty.day15;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay15 {

    @Test
    void testPart1 () throws IOException {
        final List<Integer> input = Day15.read("src/test/resources/input-day-15.txt");
        assertEquals(436L, Day15.part1(input));
    }

    // don't forget to set -Xmx1024M to prevent OOM
    @Test
    void testPart2 () throws IOException {
        final List<Integer> input = Day15.read("src/test/resources/input-day-15.txt");
        assertEquals(175594L, Day15.part2(input));
    }

    @Test
    void testGetPrevIndex() {
        List<Integer> input = new ArrayList<>();
        input.add(new Integer(0));
        input.add(new Integer(3));
        input.add(new Integer(6));

        int prevPrevIndex = Day15.getPrevIndex(input, input.get(input.size()-1), input.size()-1);
        assertEquals(-1L, prevPrevIndex);

        System.out.println("=============");
        input.add(new Integer(0));

        assertEquals(0, input.get(input.size()-1));
        prevPrevIndex = Day15.getPrevIndex(input, input.get(input.size()-1), input.size()-1);
        assertEquals(0L, prevPrevIndex);

    }

}
