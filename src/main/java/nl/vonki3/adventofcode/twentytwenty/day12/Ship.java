package nl.vonki3.adventofcode.twentytwenty.day12;

import lombok.Data;

@Data
public class Ship {
    Waypoint position = new Waypoint();
    Waypoint waypoint = null;

    public Ship() {
    }

    public Ship(final Waypoint waypoint) {
        this.waypoint = waypoint;
    }


    public void move(final Directions.Direction d, final int range) {
        move(d, range, true, null);
    }

    public void move(final Directions.Direction d, final int range, final boolean printMove, final Waypoint position) {
        if (printMove) {
            System.out.print("" + d + range + ": ");
        }

        final Waypoint wp = (position != null) ? position : ((waypoint != null) ? waypoint : this.position);
        switch (d) {
            case F: {
                System.out.print("move ");
                move(Directions.Direction.E, this.waypoint.getRightRange() * range, false, this.position);
                move(Directions.Direction.N, this.waypoint.getForwardRange() * range, false, this.position);
                break;
            }
            case E: {
                wp.addHorizontal(range);
                break;
            }
            case W: {
                wp.addHorizontal(-1 * range);
                break;
            }
            case N: {
                wp.addVertical(range);
                break;
            }
            case S: {
                wp.addVertical(-1 * range);
                break;
            }
            case R: {
                int i = range / 90;
                while (i-- > 0) {
                    System.out.print("turn from " + wp.getCurDirection());
                    wp.setCurDirection(wp.getCurDirection().right());
                    System.out.print(" to " + wp.getCurDirection() + " = ");
                }
                break;
            }
            case L: {
                int i = range / 90;
                while (i-- > 0) {
                    System.out.print("turn from " + wp.getCurDirection());
                    wp.setCurDirection(wp.getCurDirection().left());
                    System.out.print(" to " + wp.getCurDirection() + " = ");
                }
                break;
            }
            default: {
                System.out.println("onbekend");
            }
        }

        if (printMove) {
            if (waypoint != null) {
                System.out.print(", waypoint: " + waypoint.getCurDirection() + " " + waypoint.getRightRange() + " " + waypoint.getForwardRange());
            }
            System.out.println(", ship: " + this.position.getCurDirection() + " " + this.position.getRightRange() + " " + this.position.getForwardRange());
        }
    }

    public long manhattanDistance() {
        return Math.abs(position.getRightRange()) + Math.abs(position.getForwardRange());
    }

}
