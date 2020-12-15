package nl.vonki3.adventofcode.twentytwenty.day12;

import lombok.Data;

@Data
public class Waypoint {
    Directions.Direction curDirection = Directions.Direction.E;
    int rightRange = 0;
    int forwardRange = 0;

    public Waypoint() {

    }



    public Waypoint(final int horizontal, final int vertical) {
        this.rightRange = horizontal;
        this.forwardRange = vertical;
    }

    public void addHorizontal(final int range) {
        System.out.print("hor " + range + " => ");
        this.rightRange += range;
    }

    public void addVertical(final int range) {
        System.out.print("ver " + range + " => ");
        this.forwardRange += range;
    }
}
