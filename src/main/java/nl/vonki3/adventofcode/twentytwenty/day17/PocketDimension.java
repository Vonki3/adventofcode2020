package nl.vonki3.adventofcode.twentytwenty.day17;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PocketDimension {
    public static final String NEWLINE = "\n";
    public static final String DIMENSION_SEPARATOR = " | ";
    final List<List<Day17.State>> dimensionX;
    final List<List<Day17.State>> dimensionY = new ArrayList<>();
    final List<List<Day17.State>> dimensionZ = new ArrayList<>();

    public PocketDimension(final List<List<Day17.State>> initial) {
        dimensionX = initial;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimensionX.size(); i++) {
            sb.append(NEWLINE);
            printRow(dimensionX.get(i), sb);

            if (dimensionY.size()-1<i) continue;
            sb.append(DIMENSION_SEPARATOR);
            printRow(dimensionY.get(i), sb);

            if (dimensionZ.size()-1<i) continue;
            sb.append(DIMENSION_SEPARATOR);
            printRow(dimensionZ.get(i), sb);
        }

        return sb.toString();
    }

    private void printRow(final List<Day17.State> row, final StringBuilder sb) {
        for (int j = 0; j < row.size(); j++) {
            sb.append(row.get(j)).append(" ");
        }
    }
}
