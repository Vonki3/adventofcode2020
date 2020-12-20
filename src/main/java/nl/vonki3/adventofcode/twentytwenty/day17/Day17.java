package nl.vonki3.adventofcode.twentytwenty.day17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;
import nl.vonki3.adventofcode.twentytwenty.util.StringInputMapper;

public class Day17 {

    public static void main(final String[] args) throws IOException {
        PocketDimension input = Day17.read("src/main/resources/input-day-17.txt");
        long part1 = Day17.part1(input);

        System.out.println("\nDay 17-1:");
        System.out.println("solution = " + part1);

        input = Day17.read("src/main/resources/input-day-17.txt");
        long part2 = Day17.part2(input);
        System.out.println("\nDay 17-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final PocketDimension input) {
        for (int cycle = 0; cycle < 6; cycle++) {
            final List<List<State>> dimensionX = input.getDimensionX();
            final List<List<State>> dimensionY = input.getDimensionY();
            final List<List<State>> dimensionZ = input.getDimensionZ();

            for (int x = 0; x < dimensionX.size(); x++) {
                final List<State> statesX = dimensionX.get(x);
                final List<State> statesY = Optional.of(dimensionY.get(x)).orElse(Collections.emptyList());
                final List<State> statesZ = Optional.of(dimensionZ.get(x)).orElse(Collections.emptyList());


            }
        }

        return 0L;
    }

    static long part2(final PocketDimension input) {
        return 0L;
    }


    static PocketDimension read(final String fileName) throws IOException {
        System.out.println("reading");
        System.out.println("==========");

        final InputReader<List<Day17.State>> reader = new InputReader<>();
        final PocketDimension result = new PocketDimension(reader.readInput(fileName, new Day17InputMapper()));

        System.out.println(result);
        System.out.println("==========");

        return result;
    }

    enum State {ACTIVE('#'), INACTIVE('.');
        final char state;

        State(final char state) {
            this.state = state;
        }

        public static State getState(final char c) {
            return Arrays.stream(values()).filter(state -> state.state==c).findAny().orElse(INACTIVE);
        }

        @Override
        public String toString() {
            return String.valueOf(this.state);
        }
    }
}
