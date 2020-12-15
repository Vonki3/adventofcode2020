package nl.vonki3.adventofcode.twentytwenty.day12;

import java.util.InputMismatchException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Directions {
    Direction direction;
    int range;


    public enum Direction {
        N, S, E, W, L, R, F;
        public Direction right() {
            if (this.equals(N)) return E;
            if (this.equals(E)) return S;
            if (this.equals(S)) return W;
            if (this.equals(W)) return N;

            throw new InputMismatchException();
        }
        public Direction left() {
            if (this.equals(N)) return W;
            if (this.equals(W)) return S;
            if (this.equals(S)) return E;
            if (this.equals(E)) return N;

            throw new InputMismatchException();
        }
    }

}
