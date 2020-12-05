package nl.vonki3.adventofcode.twentytwenty.day3;

import java.util.ArrayList;
import java.util.List;

import nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface;

public class Day3InputMapper  implements MapInputInterface<List<Boolean>> {

    public List<Boolean> map(final String line) {
        List<Boolean> result = new ArrayList<>();

        for (final char aChar : line.toCharArray()) {
            result.add(aChar == '#');
        }

        return result;
    }
}
