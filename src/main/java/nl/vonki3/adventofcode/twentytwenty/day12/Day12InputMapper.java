package nl.vonki3.adventofcode.twentytwenty.day12;

public class Day12InputMapper implements nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface<Directions> {
    @Override
    public Directions map(final String inputPart) {
        return new Directions(
                Directions.Direction.valueOf(inputPart.substring(0,1)),
                Integer.parseInt(inputPart.substring(1))
        );
    }
}
