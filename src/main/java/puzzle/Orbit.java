package main.java.puzzle;

import main.java.hex.Hex;
import main.java.hex.HexBoard;

import java.util.ArrayList;
import java.util.List;

public class Orbit {
    public static List<Piece> getPositions(List<Hex> list, boolean transposable, HexBoard<String> board, List<Piece> forbidden, int radius) {
        Piece trial = new Piece(list);
        Piece trialCw = trial.rotateCw();
        Piece trialCcw = trial.rotateCcw();
        List<Piece> positions = new ArrayList<>();
        board.hexes().forEach((hexo) -> {
            if (trial.traslate(hexo).maxRadius() <= radius && !trial.traslate(hexo).isContainedIn(forbidden)) positions.add(trial.traslate(hexo));
            if (trialCw.traslate(hexo).maxRadius() <= radius && !trialCw.traslate(hexo).isContainedIn(forbidden)) positions.add(trialCw.traslate(hexo));
            if (trialCcw.traslate(hexo).maxRadius() <= radius && !trialCcw.traslate(hexo).isContainedIn(forbidden)) positions.add(trialCcw.traslate(hexo));
        });

        if(transposable) {
            Piece transposed = trial.transpose();
            Piece transposedCw = transposed.rotateCw();
            Piece transposedCcw = transposed.rotateCcw();
            board.hexes().forEach((hexo) -> {
                if (transposed.traslate(hexo).maxRadius() <= radius && !transposed.traslate(hexo).isContainedIn(forbidden)) positions.add(transposed.traslate(hexo));
                if (transposedCw.traslate(hexo).maxRadius() <= radius && !transposedCw.traslate(hexo).isContainedIn(forbidden)) positions.add(transposedCw.traslate(hexo));
                if (transposedCcw.traslate(hexo).maxRadius() <= radius && !transposedCcw.traslate(hexo).isContainedIn(forbidden)) positions.add(transposedCcw.traslate(hexo));
            });
        }
        return positions;
    }
}
