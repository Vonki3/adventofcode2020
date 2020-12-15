package nl.vonki3.adventofcode.twentytwenty.day12;

import java.io.IOException;
import java.util.List;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class Day12 {
    private static final Logger LOG = LoggerFactory.getLogger(Day12.class);

    public static void main(final String[] args) throws IOException {
        final InputReader<Directions> reader = new InputReader<>();
        final List<Directions> input = reader.readInput("src/main/resources/input-day-12.txt", new Day12InputMapper());

        long part1 = Day12.part1(input);
        long part2 = Day12.part2(input);

        System.out.println("\nDay 12-1:");
        System.out.println("solution = " + part1);
        System.out.println("\nDay 12-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final List<Directions> input) {
        final Ship ship = new Ship();
        for (final Directions directions : input) {
            ship.move(directions.getDirection(), directions.getRange());
        }
        return ship.manhattanDistance();
    }


    static long part2(final List<Directions> input) {
        final Ship ship = new Ship(new Waypoint(10, 1));
        for (final Directions directions : input) {
            ship.move(directions.getDirection(), directions.getRange());
        }
        return ship.manhattanDistance();
    }

}

