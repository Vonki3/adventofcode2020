package nl.vonki3.adventofcode.twentytwenty.day7;

import java.io.IOException;
import java.util.List;

import javax.naming.NameNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay7 {

    @Test
    void testPart1 () throws IOException, InstantiationException, IllegalAccessException, NameNotFoundException {
        final InputReader<Bag> reader1 = new InputReader<>();
        final List<Bag> input1 = reader1.readInput("src/test/resources/input-day-7.txt", new Day7InputMapper());
        Assertions.assertEquals(4L, Day7.part1(input1, "shiny gold"));
    }
    @Test
    void testPart2 () throws IOException, IllegalAccessException, InstantiationException, NameNotFoundException {
        final InputReader<Bag> reader2 = new InputReader<>();
        final List<Bag> input2 = reader2.readInput("src/test/resources/input-day-7-2.txt", new Day7InputMapper());

        Assertions.assertEquals(126L, Day7.part2(input2, "shiny gold"));
    }
}
