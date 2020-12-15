package nl.vonki3.adventofcode.twentytwenty.day11;

import java.util.ArrayList;
import java.util.List;

import nl.vonki3.adventofcode.twentytwenty.day8.GameConsoleInstruction;
import nl.vonki3.adventofcode.twentytwenty.day8.Operator;

public class Day11InputMapper implements nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface<SeatRow> {
    @Override
    public SeatRow map(final String inputPart) {
        final SeatRow row = new SeatRow();

        for (final char seat : inputPart.toCharArray()) {
            row.add(SeatRow.Seat.valueOf(seat));
        }

        return row;
    }
}
