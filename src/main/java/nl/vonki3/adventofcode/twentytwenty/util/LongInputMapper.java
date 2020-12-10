package nl.vonki3.adventofcode.twentytwenty.util;

public class LongInputMapper implements MapInputInterface<Long> {
    @Override
    public Long map(final String line) {
        return Long.valueOf(line);
    }
}

