package nl.vonki3.adventofcode.twentytwenty.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class InputReader<U> {

    public List<U> readInput(final String fileName, final MapInputInterface<U> mapper) throws IOException {
        List<U> result = null;

        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.map(mapper::map).collect(Collectors.toList());
        }

        return result;
    }
}
