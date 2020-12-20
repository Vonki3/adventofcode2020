package nl.vonki3.adventofcode.twentytwenty.day17;

import java.util.ArrayList;
import java.util.List;

public class Day17InputMapper implements nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface<List<Day17.State>> {
    @Override
    public List<Day17.State> map(final String line) {
        final List<Day17.State> pocket = new ArrayList<>();

        for (final char c : line.toCharArray()) {
            final Day17.State state = Day17.State.getState(c);
            pocket.add(state);
        }

        return pocket;
    }
}
