package nl.vonki3.adventofcode.twentytwenty.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class Day4 {
    private static final Logger LOG = LoggerFactory.getLogger(Day4.class);

    public static void main(final String[] args) throws IOException {
        // can't read the input with a mapper because passport info can be on multiple lines
        final List<Passport> input = read("src/main/resources/input-day-4.txt");

        final Day4 instance = new Day4();
        long part1 = instance.part1(input);
        long part2 = instance.part2(input);

        System.out.println("\nDay 4-1:");
        System.out.println("solution = " + part1);
        System.out.println("\nDay 4-2:");
        System.out.println("solution = " + part2);
    }

    static List<Passport> read(final String fileName) throws FileNotFoundException {
        final List<Passport> input = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.useDelimiter(" |\\r\\n");
            Passport passport = new Passport();
            while (scanner.hasNext()) {
                final String next = scanner.next();
                if (next.equals("")) {
                    // found a new passport, store the current and create a new passport
                    input.add(passport);
                    passport = new Passport();
                } else {
                    passport.setProperty(next);
                }
            }
            if (passport.getProperties().size() > 0) {
                input.add(passport);
            }
        }

        return input;
    }

    static long part1(final List<Passport> input) {
        return input.stream().filter(passport ->
                Arrays.stream(PassportKeys.values())
                        .allMatch(key -> !key.isMandatory() || passport.getProperties().containsKey(key))
        )
                .count();
    }

    static long part2(final List<Passport> input) {
        return input.stream().filter(passport ->
                Arrays.stream(PassportKeys.values())
                        .allMatch(key -> (!key.isMandatory() || passport.getProperties().containsKey(key)) && key.getValidation().test(passport))
        )
                .count();
    }

    @Data
    static class Passport {
        static final int KEY = 0;
        static final int VALUE = 1;
        final Map<PassportKeys, String> properties = new EnumMap<>(PassportKeys.class);

        public void setProperty(final String next) {
            final String[] input = next.split(":");
            properties.put(PassportKeys.findById(input[KEY]), input[VALUE]);
        }
    }


    static final boolean IS_MANDATORY = true;
    static final boolean IS_OPTIONAL = false;

    @AllArgsConstructor
    @Getter
    public enum PassportKeys {
        BYR("byr", "Birth Year", IS_MANDATORY, passport -> {
            return Optional.ofNullable(passport.getProperties().get(PassportKeys.findById("byr")))
                    .filter(StringUtils::isNotEmpty)
                    .map(Integer::parseInt)
                    .map(byr -> byr >= 1920 && byr <= 2002)
                    .orElse(false);
        }),
        IYR("iyr", "Issue Year", IS_MANDATORY, passport -> {
            return Optional.ofNullable(passport.getProperties().get(PassportKeys.findById("iyr")))
                    .filter(StringUtils::isNotEmpty)
                    .map(Integer::parseInt)
                    .map(iyr -> iyr >= 2010 && iyr <= 2020)
                    .orElse(false);
        }),
        EYR("eyr", "Expiration Year", IS_MANDATORY, passport -> {
            return Optional.ofNullable(passport.getProperties().get(PassportKeys.findById("eyr")))
                    .filter(StringUtils::isNotEmpty)
                    .map(Integer::parseInt)
                    .map(byr -> byr >= 2020 && byr <= 2030)
                    .orElse(false);
        }),
        HGT("hgt", "Height", IS_MANDATORY, passport -> {
            return Optional.ofNullable(passport.getProperties().get(PassportKeys.findById("hgt")))
                    .filter(StringUtils::isNotEmpty)
                    .map(sHgt -> {
                        final Matcher m = Pattern.compile("(\\d+)(cm|in)").matcher((String) sHgt);
                        if (m.find()) {
                            final int hgt = Integer.parseInt(m.group(1));
                            if ("cm".equals(m.group(2))) {
                                return hgt >= 150 && hgt <= 193;
                            } else if ("in".equals(m.group(2))) {
                                return hgt >= 59 && hgt <= 76;
                            }
                        }
                        return false;
                    })
                    .orElse(false);
        }),
        HCL("hcl", "Hair Color", IS_MANDATORY, passport -> {
            return Optional.ofNullable(passport.getProperties().get(PassportKeys.findById("hcl")))
                    .filter(StringUtils::isNotEmpty)
                    .map(hcl -> hcl.matches("^#[0-9a-f]{6}$"))
                    .orElse(false);
        }),
        ECL("ecl", "Eye Color", IS_MANDATORY, passport -> {
            return Optional.ofNullable(passport.getProperties().get(PassportKeys.findById("ecl")))
                    .filter(StringUtils::isNotEmpty)
                    .map(ecl -> ecl.matches("^amb|blu|brn|gry|grn|hzl|oth$"))
                    .orElse(false);
        }),
        PID("pid", "Passport ID", IS_MANDATORY, passport -> {
            return Optional.ofNullable(passport.getProperties().get(PassportKeys.findById("pid")))
                    .filter(StringUtils::isNotEmpty)
                    .map(pid -> pid.matches("^[0-9]{9}$"))
                    .orElse(false);
        }),
        CID("cid", "Country ID", IS_OPTIONAL, key -> true);

        private final String abbr;
        private final String full;
        private final boolean mandatory;
        private final Predicate<Passport> validation;

        public static PassportKeys findById(final String id) {
            for (PassportKeys passwordKeys : values()) {
                if (passwordKeys.getAbbr().equals(id)) {
                    return passwordKeys;
                }
            }
            return null;
        }
    }
}
