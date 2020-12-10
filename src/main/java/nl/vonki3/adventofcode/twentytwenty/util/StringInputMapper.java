package nl.vonki3.adventofcode.twentytwenty.util;

import nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface;

public class StringInputMapper implements MapInputInterface<String> {
    @Override
    public String map(final String line) {
        return line;
    }
}

