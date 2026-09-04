package main.java.puzzle;

import main.java.hex.Hex;
import main.java.hex.HexBoard;

import java.util.ArrayList;
import java.util.List;

public class IslandChecker {
    public static List<List<Hex>> getIslands(HexBoard<String> board, List<Piece> pieces) {

        List<Hex> occupied = new ArrayList<>();

        for (int i = 0; i < pieces.size(); i ++) {
            occupied.addAll(pieces.get(i).getList());
        }

        List<Hex> free = new ArrayList<>();

        board.hexes().forEach((hexo) -> {
            if (!hexo.isInList(occupied)) {
                free.add(hexo);
            }
        });

        List<List<Hex>> seeds = new ArrayList<>();
        for (int i = 0; i < free.size(); i ++) {
            List<Hex> seed = new ArrayList<Hex>();
            seed.add(free.get(i));
            seeds.add(seed);
        }

        for (int i = 0; i < seeds.size(); i ++) {
            for (int j = 0; j < free.size(); j ++) {
                if (!free.get(j).isInList(seeds.get(i))) {
                    for(int k =0; k< seeds.get(i).size(); k++) {
                        if (seeds.get(i).get(k).borders(free.get(j))) {
                            seeds.get(i).add(free.get(j));
                        }
                    }
                }
            }
        }
        return seeds;
    }

    public static boolean illegalIslands(List<List<Hex>> seeds) {
        int singletons = 0;
        for(int i = 0; i < seeds.size(); i++) {
            if (seeds.get(i).size() == 1) {
                singletons ++;
            }
            if (singletons > 1) {
//                System.out.println("2 singletons");
                return true;
            }
            if (seeds.get(i).size() < 5 && seeds.get(i).size() > 1) {
//                System.out.println("too small an island: " + seeds.get(i).size());
                return true;
            }
        }
        return false;
    }
}
