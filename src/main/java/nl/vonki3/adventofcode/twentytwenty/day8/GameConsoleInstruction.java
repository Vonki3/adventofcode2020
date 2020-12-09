package nl.vonki3.adventofcode.twentytwenty.day8;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameConsoleInstruction {
    private Operator operator;
    private int argument;
    private boolean executed;

    public GameConsoleInstruction copy() {
//        Operator operator;
//        switch (this.operator) {
//            case ACC:
//                operator = Operator.ACC;
//                break;
//            case JMP:
//                operator = Operator.NOP;
//                break;
//            case NOP:
//                operator = Operator.JMP;
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + this.operator);
//        }

        return new GameConsoleInstruction(this.operator, this.argument, false);
    }
}
