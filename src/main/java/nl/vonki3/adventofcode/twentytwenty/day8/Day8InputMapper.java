package nl.vonki3.adventofcode.twentytwenty.day8;

public class Day8InputMapper implements nl.vonki3.adventofcode.twentytwenty.util.MapInputInterface<GameConsoleInstruction> {
    @Override
    public GameConsoleInstruction map(final String inputPart) {
        final String[] instructionParts = inputPart.split(" ");
        return new GameConsoleInstruction(Operator.valueOf(instructionParts[0].toUpperCase()), Integer.parseInt(instructionParts[1]), false);
    }
}
