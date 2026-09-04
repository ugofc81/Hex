package main.java.puzzle;

import main.java.hex.Hex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Piece {
    private List<Hex> list;

    public Piece(List<Hex> l) {
        Collections.sort(l);
        this.list = l;
    }

    @Override
    public String toString() {
        return "puzzle.Piece{" +
                "list=" + list +
                '}';
    }

    public Piece transpose() {
        List<Hex> transp = new ArrayList<>();
        for (Hex hex : this.list) {
            transp.add(hex.transpose());
        }
        Collections.sort(transp);
        return new Piece(transp);
    }

    public Piece traslate(Hex o) {
        List<Hex> transp = new ArrayList<>();
        for (Hex hex : this.list) {
            transp.add(hex.add(o));
        }
        Collections.sort(transp);
        return new Piece(transp);
    }

    public int maxRadius() {
        int result = 0;
        for (Hex hex : this.list) {
            if (hex.module() > result) {
                result = hex.module();
            }
        }
        return result;
    }

    public Piece rotateCw() {
        List<Hex> transp = new ArrayList<>();
        int length = this.list.size();
        for (Hex hex : this.list) {
            transp.add(hex.rotateCW().rotateCW());
        }
        Collections.sort(transp);
        return new Piece(transp);
    }

    public Piece rotateCcw() {
        List<Hex> transp = new ArrayList<>();
        int length = this.list.size();
        for (Hex hex : this.list) {
            transp.add(hex.rotateCCW().rotateCCW());
        }
        Collections.sort(transp);
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

    public boolean coincides(Piece p) {
        for (int i = 0; i < this.list.size(); i++) {
            if (!this.list.get(i).equals(p.list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isContainedIn(List<Piece> piecesList) {
        for (int i = 0; i < piecesList.size(); i++) {
            if (piecesList.get(i).coincides(this)) {
                return true;
            }
        }
        return false;
    }

    public List<Hex> getList() {
        return list;
    }
}
