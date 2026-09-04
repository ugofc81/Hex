package main.java.puzzle;

import java.util.ArrayList;
import java.util.List;

public class SymmetryBreaker {
    public static List<Piece> getStar(Piece firstPosition) {
        List<Piece> positions = new ArrayList<>();
        positions.add(firstPosition);
        positions.add(firstPosition.rotateCw());
        positions.add(firstPosition.rotateCcw());
        positions.add(firstPosition.transpose());
        positions.add(firstPosition.transpose().rotateCw());
        positions.add(firstPosition.transpose().rotateCcw());

        return positions;
    }
}
