package nl.vonki3.adventofcode.twentytwenty.day11;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class Day11 {
    private static final Logger LOG = LoggerFactory.getLogger(Day11.class);
    static final List<Point> directions = Arrays.asList(new Point(-1, -1), new Point(0, -1), new Point(1, -1), new Point(-1, 0), new Point(1, 0), new Point(-1, 1), new Point(0, 1), new Point(1, 1));

    public static void main(final String[] args) throws IOException {
        final InputReader<SeatRow> reader = new InputReader<>();
        final List<SeatRow> input = reader.readInput("src/main/resources/input-day-11.txt", new Day11InputMapper());

        long part1 = Day11.part1(input);
        long part2 = Day11.part2(input);

        System.out.println("\nDay 11-1:");
        System.out.println("solution = " + part1);
        System.out.println("\nDay 11-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final List<SeatRow> input) {
        print(input, -1, -1);

        List<SeatRow> lastInput = new ArrayList<>();
        List<SeatRow> newInput = new ArrayList<>(input);
        while (!equals(lastInput, newInput)) {
            lastInput = newInput;
            newInput = evolve(lastInput);
            print(newInput, -1, -1);
        }

        return count(lastInput);
    }


    static long part2(final List<SeatRow> input) {
        print(input, -1, -1);

        List<SeatRow> lastInput = new ArrayList<>();
        List<SeatRow> newInput = new ArrayList<>(input);
        while (!equals(lastInput, newInput)) {
            lastInput = newInput;
            newInput = evolve2(lastInput);
            print(newInput, -1, -1);
        }

        return count(lastInput);
    }


    private static long count(final List<SeatRow> seatMap) {
        int nrOccupiedSeats = 0;

        SeatRow seatRow;
        for (int i = 0; i < seatMap.size(); i++) {
            seatRow = seatMap.get(i);
            for (int j = 0; j < seatRow.size(); j++) {
                if (seatRow.get(j) == SeatRow.Seat.OCCUPIED_SEAT) {
                    nrOccupiedSeats++;
                }
            }
        }

        return nrOccupiedSeats;
    }


    private static boolean equals(final List<SeatRow> left, final List<SeatRow> right) {
        if (left.size() != right.size()) {
            return false;
        }

        for (int i = 0; i < left.size(); i++) {
            for (int j = 0; j < right.size(); j++) {
                if (left.get(i).get(j) != (right.get(i).get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


    private static List<SeatRow> evolve(final List<SeatRow> seatMap) {
        final List<SeatRow> evolvedSeatMap = new ArrayList<>();

        SeatRow curSeatRow;
        for (int i = 0; i < seatMap.size(); i++) {
            curSeatRow = seatMap.get(i);

            final SeatRow newSeatRow = new SeatRow();
            evolvedSeatMap.add(newSeatRow);

            SeatRow.Seat evolvedSeat;
            for (int j = 0; j < curSeatRow.size(); j++) {
                evolvedSeat = calculate(seatMap, i, j);
                newSeatRow.add(evolvedSeat);
            }
        }

        return evolvedSeatMap;
    }


    private static List<SeatRow> evolve2(final List<SeatRow> seatMap) {
        final List<SeatRow> evolvedSeatMap = new ArrayList<>();

        SeatRow curSeatRow;
        for (int i = 0; i < seatMap.size(); i++) {
            curSeatRow = seatMap.get(i);

            final SeatRow newSeatRow = new SeatRow();
            evolvedSeatMap.add(newSeatRow);

            SeatRow.Seat evolvedSeat;
            for (int j = 0; j < curSeatRow.size(); j++) {
                evolvedSeat = calculate2(seatMap, i, j);
                newSeatRow.add(evolvedSeat);
            }
        }

        return evolvedSeatMap;
    }

    private static SeatRow.Seat calculate(final List<SeatRow> seatMap, int curRowNr, int curSeatNr) {
        final SeatRow.Seat curSeat = seatMap.get(curRowNr).get(curSeatNr);

        if (curSeat == SeatRow.Seat.FLOOR) {
            return curSeat;
        }

        final List<SeatRow.Seat> seats = getDirectNeighbours(seatMap, new Point(curSeatNr, curRowNr));

        long count = seats.stream().filter(s -> s == SeatRow.Seat.OCCUPIED_SEAT).count();

        if (curSeat == SeatRow.Seat.EMPTY_SEAT && count == 0) {
            return SeatRow.Seat.OCCUPIED_SEAT;
        }

        if (curSeat == SeatRow.Seat.OCCUPIED_SEAT && count >= 4) {
            return SeatRow.Seat.EMPTY_SEAT;
        }

        return curSeat;
    }

    private static SeatRow.Seat calculate2(final List<SeatRow> seatMap, int curRowNr, int curSeatNr) {
        final SeatRow.Seat curSeat = seatMap.get(curRowNr).get(curSeatNr);

        if (curSeat == SeatRow.Seat.FLOOR) {
            return curSeat;
        }

        final List<SeatRow.Seat> seats = getVisibleSeats(seatMap, new Point(curSeatNr, curRowNr));


//        print (seatMap, curRowNr, curSeatNr);
//        System.out.println("(" + curRowNr + "," + curSeatNr + ") ");
//        printRow(seats, true, curSeatNr);
        long count = seats.stream().filter(s -> s == SeatRow.Seat.OCCUPIED_SEAT).count();

        if (curSeat == SeatRow.Seat.EMPTY_SEAT && count == 0) {
            return SeatRow.Seat.OCCUPIED_SEAT;
        }

        if (curSeat == SeatRow.Seat.OCCUPIED_SEAT && count >= 5) {
            return SeatRow.Seat.EMPTY_SEAT;
        }

        return curSeat;
    }

    private static List<SeatRow.Seat> getDirectNeighbours(final List<SeatRow> seatMap, final Point curSeat) {
        final List<SeatRow.Seat> seats = new ArrayList<>();

        directions.forEach(point -> {
            final Point newPoint = new Point(curSeat);
            newPoint.translate(point.x, point.y);

            try {
                seats.add(seatMap.get(newPoint.y).get(newPoint.x));
            } catch (final IndexOutOfBoundsException e) {
                // do nothing
            }
        });

//        System.out.println("==========");
//        printRow(seats, false, -1);
        return seats;
    }

    private static List<SeatRow.Seat> getVisibleSeats(final List<SeatRow> seatMap, final Point curSeat) {
        final List<SeatRow.Seat> seats = new ArrayList<>();

        directions.forEach(point -> {
            boolean endstate = false;
            Point newPoint = curSeat;
            while (!endstate) {
                newPoint = new Point(newPoint);
                newPoint.translate(point.x, point.y);

                try {
                    final SeatRow.Seat possibleSeat = seatMap.get(newPoint.y).get(newPoint.x);
                    if (possibleSeat != SeatRow.Seat.FLOOR) {
                        seats.add(possibleSeat);
                        endstate = true;
                    }
                } catch (final IndexOutOfBoundsException e) {
                    endstate = true;
                }
            }
        });

        System.out.println("==========");
        printRow(seats, false, -1);
        return seats;
    }

    private static void printRow(final List<SeatRow.Seat> seats, final boolean curRow, final int seatNr) {
        StringBuilder row = new StringBuilder();
        for (int j = 0; j < seats.size(); j++) {
            final SeatRow.Seat seat = seats.get(j);
            if (curRow && seatNr == j) {
                row.append(seat.toStringHighlighted());
            } else {
                row.append(seat.toString());
            }
        }
        System.out.println(row.toString());
        row.setLength(0);
    }

    private static void print(final List<SeatRow> seatMap, final int rowNr, final int seatNr) {
        System.out.println("===========");
        for (int i = 0; i < seatMap.size(); i++) {
            printRow(seatMap.get(i), rowNr == i, seatNr);
        }
    }
}

