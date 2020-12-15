package nl.vonki3.adventofcode.twentytwenty.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Memory extends HashMap<Long, Long> {
    final Map<Long, List<Long>> additional = new HashMap<>();

    public Map<Long, List<Long>> getAdditional() {
        return additional;
    }

    public void put(final Long key, final String value) {
        final List<String> allPossibleValues = new ArrayList<>();

        recursive(value, allPossibleValues);
        List<Long> allPossibleValuesAsLong = allPossibleValues.stream().map(s -> Long.parseLong(s, 2)).collect(Collectors.toList());
        additional.put(key, allPossibleValuesAsLong);
    }

    public void recursive(final String value, final List<String> result) {

        int indexOfX = value.indexOf("X");
        if (indexOfX != -1) {
            calc(value, indexOfX).forEach(s -> {
                    recursive(s, result);
            });
        }
        else {
            System.out.println("adding " + value);
            result.add(value);
        }
    }

    public List<String> calc(final String value, final int indexOfX) {
        return (value.charAt(indexOfX) == 'X')
                ? new ArrayList<>(Arrays.asList(value.replaceFirst("X", "0"), value.replaceFirst("X", "1")))
                : new ArrayList<>(Arrays.asList(value));
    }

}
