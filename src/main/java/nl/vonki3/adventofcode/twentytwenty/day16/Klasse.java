package nl.vonki3.adventofcode.twentytwenty.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;

@Data
public class Klasse {
    final String name;
    final List<Integer> validNumbers;

    public Klasse(final String line) {
        final String[] split = line.split(":");
        validNumbers = new ArrayList<>();
        this.name = split[0];

        final Pattern pattern = Pattern.compile("(\\d+)-(\\d+) or (\\d+)-(\\d+)");
        final Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            final int firstNr = Integer.parseInt(matcher.group(1));
            final int secondNr = Integer.parseInt(matcher.group(2));
            final int thirdNr = Integer.parseInt(matcher.group(3));
            final int fourthNr = Integer.parseInt(matcher.group(4));

            for (int i = firstNr; i <= secondNr; i++) {
                validNumbers.add(i);
            }
            for (int i = thirdNr; i <= fourthNr; i++) {
                validNumbers.add(i);
            }
        }
    }
}
