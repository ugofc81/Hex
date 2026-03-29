import java.util.ArrayList;
import java.util.List;

public class Positions {
    public static List<Piece> getPositions(List<Hex> list, boolean transposable, HexBoard<String> board, int radius) {
        Piece trial = new Piece(list);
        Piece trialCw = trial.rotateCw();
        Piece trialCcw = trial.rotateCcw();
        List<Piece> positions = new ArrayList<>();
        board.hexes().forEach((hexo) -> {
            if (trial.traslate(hexo).maxRadius() <= radius) positions.add(trial.traslate(hexo));
            if (trialCw.traslate(hexo).maxRadius() <= radius) positions.add(trialCw.traslate(hexo));
            if (trialCcw.traslate(hexo).maxRadius() <= radius) positions.add(trialCcw.traslate(hexo));
        });

        if(transposable) {
            Piece transposed = trial.transpose();
            Piece transposedCw = transposed.rotateCw();
            Piece transposedCcw = transposed.rotateCcw();
            board.hexes().forEach((hexo) -> {
                if (transposed.traslate(hexo).maxRadius() <= radius) positions.add(transposed.traslate(hexo));
                if (transposedCw.traslate(hexo).maxRadius() <= radius) positions.add(transposedCw.traslate(hexo));
                if (transposedCcw.traslate(hexo).maxRadius() <= radius) positions.add(transposedCcw.traslate(hexo));
            });
        }
        return positions;
    }
}
