package nl.vonki3.adventofcode.twentytwenty.day8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NameNotFoundException;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import nl.vonki3.adventofcode.twentytwenty.util.InputReader;

public class Day8 {
    private static final Logger LOG = LoggerFactory.getLogger(Day8.class);

    public static void main(final String[] args) throws IOException, NameNotFoundException {
        final InputReader<GameConsoleInstruction> reader = new InputReader<>();
        final List<GameConsoleInstruction> input = reader.readInput("src/main/resources/input-day-8.txt", new Day8InputMapper());

        long part1 = Day8.part1(input);
        long part2 = Day8.part2(input);

        System.out.println("\nDay 8-1:");
        System.out.println("solution = " + part1);
        System.out.println("\nDay 8-2:");
        System.out.println("solution = " + part2);
    }

    static long part1(final List<GameConsoleInstruction> input) {
        return testInput(input, true);
    }

    static long part2(final List<GameConsoleInstruction> input) {
        final List<List<GameConsoleInstruction>> allPossibleInputs = new ArrayList<>();
        input.stream()
                .filter(instruction -> instruction.getOperator() == Operator.JMP || instruction.getOperator() == Operator.NOP)
                .forEach(instruction -> {
                    final List<GameConsoleInstruction> possibleInstructionsInstance = new ArrayList<>();
                    for (GameConsoleInstruction cpInstruction : input) {

                        final GameConsoleInstruction copy = cpInstruction.copy();
                        if (input.indexOf(cpInstruction) == input.indexOf(instruction)) {
                            changeOperator(copy);
                        }
                        possibleInstructionsInstance.add(copy);
                    }
                    allPossibleInputs.add(possibleInstructionsInstance);
                });

        int i = 0;
        for (List<GameConsoleInstruction> instructions : allPossibleInputs) {
            System.out.println("Test run " + ++i + "/" + allPossibleInputs.size());
            System.out.println("=================");
            instructions.forEach(System.out::println);

            try {
                final int testResult = testInput(instructions, false);
                if (testResult != 0L) {
                    System.out.println("finished");
                    return testResult;
                }
            } catch (final Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return 0L;
    }

    private static void changeOperator(final GameConsoleInstruction curInstruction) {
        if (curInstruction.getOperator() == Operator.JMP) {
            curInstruction.setOperator(Operator.NOP);
        } else if (curInstruction.getOperator() == Operator.NOP) {
            curInstruction.setOperator(Operator.JMP);
        }
    }

    private static int testInput(final List<GameConsoleInstruction> input, final boolean useIsExecuted) {
        int accumulator = 0;
        int curInstruction = 0;
        GameConsoleInstruction instruction = input.get(0);

        while (!instruction.isExecuted()) {
            instruction.setExecuted(true);
            switch (instruction.getOperator()) {
                case NOP:
                    curInstruction++;
                    break;
                case JMP:
                    curInstruction += instruction.getArgument();
                    break;
                case ACC:
                    accumulator += instruction.getArgument();
                    curInstruction++;
                    break;
                default:
                    System.out.println("unknown operator " + instruction.getOperator());
            }
            System.out.println(instruction.getOperator() + " " + instruction.getArgument() + ": accumulator=" + accumulator + " nextInstruction=" + curInstruction);
            if (curInstruction==input.size()) {
                return accumulator;
            }

            instruction = input.get(curInstruction);
        }

        System.out.println("curInstruction=" + curInstruction + "/" + input.size() + ", " + accumulator + "\n");
        return !useIsExecuted && curInstruction < input.size() ? 0 : accumulator;
    }
}
