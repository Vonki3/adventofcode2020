package nl.vonki3.adventofcode.twentytwenty.util;

import nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface;

public class IntegerInputMapper implements MapInputInterface<Integer> {
    @Override
    public Integer map(final String line) {
        return Integer.valueOf(line);
    }
}

