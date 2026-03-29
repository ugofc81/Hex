import java.util.ArrayList;
import java.util.List;

public class Piece {
    private List<Hex> list;

    public Piece(List<Hex> l) {
        this.list = l;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "list=" + list +
                '}';
    }

    public Piece transpose() {
        List<Hex> transp = new ArrayList<>();
        int length = this.list.size();
        for(int i = 0; i < length; i++) {
            transp.add(this.list.get(i).transpose());
        }
        return new Piece(transp);
    }

    public Piece traslate(Hex o) {
        List<Hex> transp = new ArrayList<>();
        int length = this.list.size();
        for(int i = 0; i < length; i++) {
            transp.add(this.list.get(i).add(o));
        }
        return new Piece(transp);
    }

    public int maxRadius() {
        int result = 0;
        for (int i = 0; i < this.list.size(); i ++) {
            if (this.list.get(i).module() > result) {
                result = this.list.get(i).module();
            }
        }
        return result;
    }

    public Piece rotateCw() {
        List<Hex> transp = new ArrayList<>();
        int length = this.list.size();
        for(int i = 0; i < length; i++) {
            transp.add(this.list.get(i).rotateCW().rotateCW());
        }
        return new Piece(transp);
    }

    public Piece rotateCcw() {
        List<Hex> transp = new ArrayList<>();
        int length = this.list.size();
        for(int i = 0; i < length; i++) {
            transp.add(this.list.get(i).rotateCCW().rotateCCW());
        }
        return new Piece(transp);
    }

    public boolean collides(Piece p) {
        for (int i = 0; i < this.list.size(); i++) {
            for (int j = 0; j < p.list.size(); j++) {
                if (this.list.get(i).equals(p.list.get(j)))
                    return true;
            }
        }
        return false;
    }
}
