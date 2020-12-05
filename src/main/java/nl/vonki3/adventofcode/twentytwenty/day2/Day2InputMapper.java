package nl.vonki3.adventofcode.twentytwenty.day2;

import nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface;

public class Day2InputMapper implements MapInputInterface<Day2Input> {
    public static final int NR_TIMES_INDEX = 0;
    public static final int PWD_CHAR_INDEX = 1;
    public static final int PASSWORD_INDEX = 2;
    public static final int MIN_NR_TIMES_INDEX = 0;
    public static final int MAX_NR_TIMES_INDEX = 1;

    public Day2Input map(final String line) {
        final String[] elements = line.split(" ");
        final String[] nrTimes = elements[NR_TIMES_INDEX].split("-");
        final char aChar = elements[PWD_CHAR_INDEX].charAt(0);
        final String password = elements[PASSWORD_INDEX];

        return new Day2Input(Integer.parseInt(nrTimes[MIN_NR_TIMES_INDEX]), Integer.parseInt(nrTimes[MAX_NR_TIMES_INDEX]), aChar, password);
    }
}
