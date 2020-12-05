package nl.vonki3.adventofcode.twentytwenty.day2;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Day2Input {
    int min;
    int max;
    char aChar;
    String password;

    public boolean isValidPartOne() {
        final int nrTimes = StringUtils.countMatches(password, aChar);
        return nrTimes >= min && nrTimes <= max;
    }

    public boolean isValidPartTwo() {
        boolean match1 = password.charAt(min - 1) == aChar;
        boolean match2 = password.charAt(max - 1) == aChar;
        return match1 ^ match2;
    }

}
