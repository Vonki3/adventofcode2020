package nl.vonki3.adventofcode.twentytwenty.day11;

import java.util.ArrayList;

public class SeatRow extends ArrayList<SeatRow.Seat> {

    public enum Seat {
        FLOOR, EMPTY_SEAT, OCCUPIED_SEAT;

        public static Seat valueOf(final char input) {
            switch (input) {
                case '.': return Seat.FLOOR;
                case 'L': return Seat.EMPTY_SEAT;
                case '#': return Seat.OCCUPIED_SEAT;
            }

            throw new IllegalArgumentException(input + " is not a valid SeatRow.Seat enum value");
        }

        @Override
        public String toString() {
            switch (this) {
                case FLOOR: return ",";
                case EMPTY_SEAT: return "L";
                case OCCUPIED_SEAT: return "#";
            }
            return "?";
        }

        public String toStringHighlighted() {
            switch (this) {
                case FLOOR: return ".";
                case EMPTY_SEAT: return "l";
                case OCCUPIED_SEAT: return "*";
            }
            return "?";
        }
    }


}
