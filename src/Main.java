import javax.swing.text.Position;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        List<Hex> listA = new ArrayList<>();
        listA.add(Hex.of(0,0));
        listA.add(Hex.of(-1,0));
        listA.add(Hex.of(1,0));
        listA.add(Hex.of(2,-1));
        listA.add(Hex.of(1,1));
        boolean listATransposable = false;

        List<Hex> listB = new ArrayList<>();
        listB.add(Hex.of(0,0));
        listB.add(Hex.of(0,-1));
        listB.add(Hex.of(0,-2));
        listB.add(Hex.of(-1,1));
        listB.add(Hex.of(-2,2));
        boolean listBTransposable = false;

        List<Hex> listC = new ArrayList<>();
        listC.add(Hex.of(0,0));
        listC.add(Hex.of(-1,0));
        listC.add(Hex.of(1,0));
        listC.add(Hex.of(0,1));
        listC.add(Hex.of(0,2));
        boolean listCTransposable = true;

        List<Hex> listD = new ArrayList<>();
        listD.add(Hex.of(0,0));
        listD.add(Hex.of(-1,0));
        listD.add(Hex.of(1,0));
        listD.add(Hex.of(0,-1));
        listD.add(Hex.of(0,-2));
        boolean listDTransposable = true;

        List<Hex> listE = new ArrayList<>();
        listE.add(Hex.of(0,0));
        listE.add(Hex.of(-1,0));
        listE.add(Hex.of(1,0));
        listE.add(Hex.of(1,-1));
        listE.add(Hex.of(2,-1));
        boolean listETransposable = true;

        List<Hex> listG = new ArrayList<>();
        listG.add(Hex.of(0,0));
        listG.add(Hex.of(-1,0));
        listG.add(Hex.of(1,0));
        listG.add(Hex.of(-1,1));
        listG.add(Hex.of(-2,1));
        boolean listGTransposable = true;

        List<Hex> listH = new ArrayList<>();
        listH.add(Hex.of(0,0));
        listH.add(Hex.of(-1,0));
        listH.add(Hex.of(1,0));
        listH.add(Hex.of(1,1));
        listH.add(Hex.of(0,-1));
        boolean listHTransposable = true;

        List<Hex> listI = new ArrayList<>();
        listI.add(Hex.of(0,0));
        listI.add(Hex.of(-1,0));
        listI.add(Hex.of(1,0));
        listI.add(Hex.of(0,1));
        listI.add(Hex.of(-1,-1));
        boolean listITransposable = true;

        List<Hex> listJ = new ArrayList<>();
        listJ.add(Hex.of(0,0));
        listJ.add(Hex.of(-1,0));
        listJ.add(Hex.of(1,0));
        listJ.add(Hex.of(2,0));
        listJ.add(Hex.of(-2,1));
        boolean listJTransposable = true;

        List<Hex> listK = new ArrayList<>();
        listK.add(Hex.of(0,0));
        listK.add(Hex.of(-1,0));
        listK.add(Hex.of(1,0));
        listK.add(Hex.of(2,-1));
        listK.add(Hex.of(3,-1));
        boolean listKTransposable = true;

        List<Hex> listL = new ArrayList<>();
        listL.add(Hex.of(0,0));
        listL.add(Hex.of(-1,0));
        listL.add(Hex.of(1,0));
        listL.add(Hex.of(2,0));
        listL.add(Hex.of(1,1));
        boolean listLTransposable = true;

        List<Hex> listM = new ArrayList<>();
        listM.add(Hex.of(0,0));
        listM.add(Hex.of(-1,0));
        listM.add(Hex.of(1,0));
        listM.add(Hex.of(1,-1));
        listM.add(Hex.of(-1,-1));
        boolean listMTransposable = true;

        Collections.sort(listA);
        Collections.sort(listB);
        Collections.sort(listC);
        Collections.sort(listD);
        Collections.sort(listE);
        Collections.sort(listG);
        Collections.sort(listH);
        Collections.sort(listI);
        Collections.sort(listJ);
        Collections.sort(listK);
        Collections.sort(listL);
        Collections.sort(listM);

        int RADIUS = 4;
        HexBoard<String> boardAll = HexBoard.hexagonal(RADIUS, "plains");

        Piece forbiddenA0 = new Piece(listA).traslate(Hex.of(-3, 3));
        List<Piece> forbiddenA = Positions.getStar(forbiddenA0);
        Piece forbiddenB0 = new Piece(listB).traslate(Hex.of(-2, 0));
        List<Piece> forbiddenB = Positions.getStar(forbiddenB0);
        Piece forbiddenC0 = new Piece(listC).traslate(Hex.of(-3, 2));
        List<Piece> forbiddenC = Positions.getStar(forbiddenC0);
        Piece forbiddenD0 = new Piece(listD).traslate(Hex.of(3, -2));
        List<Piece> forbiddenD = Positions.getStar(forbiddenD0);
        Piece forbiddenH0 = new Piece(listH).traslate(Hex.of(-3, 3));
        List<Piece> forbiddenH = Positions.getStar(forbiddenH0);
        Piece forbiddenI0 = new Piece(listI).traslate(Hex.of(3, -3));
        List<Piece> forbiddenI = Positions.getStar(forbiddenI0);
        Piece forbiddenJ0 = new Piece(listJ).traslate(Hex.of(-1, 3));
        List<Piece> forbiddenJ = Positions.getStar(forbiddenJ0);
        Piece forbiddenK0 = new Piece(listK).traslate(Hex.of(0, -3));
        List<Piece> forbiddenK = Positions.getStar(forbiddenK0);
        Piece forbiddenL0 = new Piece(listL).traslate(Hex.of(-3, 3));
        List<Piece> forbiddenL = Positions.getStar(forbiddenL0);

        List<Piece> positionsA = Positions.getPositions(listA, listATransposable, boardAll, forbiddenA, RADIUS);
        List<Piece> positionsB = Positions.getPositions(listB, listBTransposable, boardAll, forbiddenB, RADIUS);
        List<Piece> positionsC = Positions.getPositions(listC, listCTransposable, boardAll, forbiddenC, RADIUS);
        List<Piece> positionsD = Positions.getPositions(listD, listDTransposable, boardAll, forbiddenD, RADIUS);
        List<Piece> positionsE = Positions.getPositions(listE, listETransposable, boardAll, forbiddenA, RADIUS);
        List<Piece> positionsG = Positions.getPositions(listG, listGTransposable, boardAll, forbiddenA, RADIUS);
        List<Piece> positionsH = Positions.getPositions(listH, listHTransposable, boardAll, forbiddenH, RADIUS);
        List<Piece> positionsI = Positions.getPositions(listI, listITransposable, boardAll, forbiddenI, RADIUS);
        List<Piece> positionsJ = Positions.getPositions(listJ, listJTransposable, boardAll, forbiddenJ, RADIUS);
        List<Piece> positionsK = Positions.getPositions(listK, listKTransposable, boardAll, forbiddenK, RADIUS);
        List<Piece> positionsL = Positions.getPositions(listL, listLTransposable, boardAll, forbiddenL, RADIUS);
        List<Piece> positionsM = Positions.getPositions(listM, listMTransposable, boardAll, forbiddenA, RADIUS);

        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsA);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsB);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsC);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsD);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsE);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsG);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsH);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsI);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsJ);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsK);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsL);
        System.out.println(new Date().toInstant().toString());
        System.out.println(positionsM);
        System.out.println(new Date().toInstant().toString());

        System.out.println(positionsA.size());
        System.out.println(positionsB.size());
        System.out.println(positionsC.size());
        System.out.println(positionsD.size());
        System.out.println(positionsE.size());
        System.out.println(positionsG.size());
        System.out.println(positionsH.size());
        System.out.println(positionsI.size());
        System.out.println(positionsJ.size());
        System.out.println(positionsK.size());
        System.out.println(positionsL.size());
        System.out.println(positionsM.size());

        for (int i = 0; i < positionsA.size(); i++) {
            for(int j = 0; j < positionsB.size(); j++){
                if (positionsB.get(j).collides(positionsA.get(i))){
                    continue;
                }
                for(int k = 0; k < positionsC.size(); k++){
                    if (
                            positionsC.get(k).collides(positionsA.get(i))
                            || positionsC.get(k).collides(positionsB.get(j))
                    ){
                        continue;
                    }
                    for(int l = 0; l < positionsD.size(); l++){
                        if (
                                positionsD.get(l).collides(positionsA.get(i)) ||
                                        positionsD.get(l).collides(positionsB.get(j)) ||
                                        positionsD.get(l).collides(positionsC.get(k))
                        ){
                            continue;
                        }

                        for(int m = 0; m < positionsE.size(); m++){
                            if (
                                    positionsE.get(m).collides(positionsA.get(i)) ||
                                            positionsE.get(m).collides(positionsB.get(j)) ||
                                            positionsE.get(m).collides(positionsC.get(k)) ||
                                            positionsE.get(m).collides(positionsD.get(l))
                            ){
                                continue;
                            }


                            for(int n = 0; n < positionsG.size(); n++){
                                if (
                                        positionsG.get(n).collides(positionsA.get(i)) ||
                                                positionsG.get(n).collides(positionsB.get(j)) ||
                                                positionsG.get(n).collides(positionsC.get(k)) ||
                                                positionsG.get(n).collides(positionsD.get(l)) ||
                                                positionsG.get(n).collides(positionsE.get(m))
                                ){
                                    continue;
                                }



                                for(int o = 0; o < positionsH.size(); o++){
                                    if (
                                            positionsH.get(o).collides(positionsA.get(i)) ||
                                                    positionsH.get(o).collides(positionsB.get(j)) ||
                                                    positionsH.get(o).collides(positionsC.get(k)) ||
                                                    positionsH.get(o).collides(positionsD.get(l)) ||
                                                    positionsH.get(o).collides(positionsE.get(m)) ||
                                                    positionsH.get(o).collides(positionsG.get(n))
                                    ) {
                                        continue;
                                    }




                                    for(int p = 0; p < positionsI.size(); p++){
                                        if (
                                                positionsI.get(p).collides(positionsA.get(i)) ||
                                                        positionsI.get(p).collides(positionsB.get(j)) ||
                                                        positionsI.get(p).collides(positionsC.get(k)) ||
                                                        positionsI.get(p).collides(positionsD.get(l)) ||
                                                        positionsI.get(p).collides(positionsE.get(m)) ||
                                                        positionsI.get(p).collides(positionsG.get(n)) ||
                                                        positionsI.get(p).collides(positionsH.get(o))
                                        ) {
                                            continue;
                                        }





                                        for(int q = 0; q < positionsJ.size(); q++){
                                            if (
                                                    positionsJ.get(q).collides(positionsA.get(i)) ||
                                                            positionsJ.get(q).collides(positionsB.get(j)) ||
                                                            positionsJ.get(q).collides(positionsC.get(k)) ||
                                                            positionsJ.get(q).collides(positionsD.get(l)) ||
                                                            positionsJ.get(q).collides(positionsE.get(m)) ||
                                                            positionsJ.get(q).collides(positionsG.get(n)) ||
                                                            positionsJ.get(q).collides(positionsH.get(o)) ||
                                                            positionsJ.get(q).collides(positionsI.get(p))
                                            ) {
                                                continue;
                                            }






                                            for(int r = 0; r < positionsK.size(); r++){
                                                if (
                                                        positionsK.get(r).collides(positionsA.get(i)) ||
                                                                positionsK.get(r).collides(positionsB.get(j)) ||
                                                                positionsK.get(r).collides(positionsC.get(k)) ||
                                                                positionsK.get(r).collides(positionsD.get(l)) ||
                                                                positionsK.get(r).collides(positionsE.get(m)) ||
                                                                positionsK.get(r).collides(positionsG.get(n)) ||
                                                                positionsK.get(r).collides(positionsH.get(o)) ||
                                                                positionsK.get(r).collides(positionsI.get(p)) ||
                                                                positionsK.get(r).collides(positionsJ.get(q))
                                                ) {
                                                    continue;
                                                }






                                                for(int s = 0; s < positionsL.size(); s++){
                                                    if (
                                                            positionsL.get(s).collides(positionsA.get(i)) ||
                                                                    positionsL.get(s).collides(positionsB.get(j)) ||
                                                                    positionsL.get(s).collides(positionsC.get(k)) ||
                                                                    positionsL.get(s).collides(positionsD.get(l)) ||
                                                                    positionsL.get(s).collides(positionsE.get(m)) ||
                                                                    positionsL.get(s).collides(positionsG.get(n)) ||
                                                                    positionsL.get(s).collides(positionsH.get(o)) ||
                                                                    positionsL.get(s).collides(positionsI.get(p)) ||
                                                                    positionsL.get(s).collides(positionsJ.get(q)) ||
                                                                    positionsL.get(s).collides(positionsK.get(r))
                                                    ) {
                                                        continue;
                                                    }

                                                    for(int t = 0; t < positionsM.size(); t++){
                                                        if (
                                                                positionsM.get(t).collides(positionsA.get(i)) ||
                                                                        positionsM.get(t).collides(positionsB.get(j)) ||
                                                                        positionsM.get(t).collides(positionsC.get(k)) ||
                                                                        positionsM.get(t).collides(positionsD.get(l)) ||
                                                                        positionsM.get(t).collides(positionsE.get(m)) ||
                                                                        positionsM.get(t).collides(positionsG.get(n)) ||
                                                                        positionsM.get(t).collides(positionsH.get(o)) ||
                                                                        positionsM.get(t).collides(positionsI.get(p)) ||
                                                                        positionsM.get(t).collides(positionsJ.get(q)) ||
                                                                        positionsM.get(t).collides(positionsK.get(r)) ||
                                                                        positionsM.get(t).collides(positionsL.get(s))
                                                        ) {
                                                            continue;
                                                        }
                                                        System.out.println("dodici pezzi " + positionsA.get(i) + " " + positionsB.get(j) + " " + positionsC.get(k) + " " + positionsD.get(l) + " " + positionsE.get(m) + " " + positionsG.get(n) + " " + positionsH.get(o) + " " + positionsI.get(p) + " " + positionsJ.get(q) + " " + positionsK.get(r) + " " + positionsL.get(s) + " " + positionsM.get(t));
                                                    }
                                                }                                                         }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.

            // Build a radius-3 board, mark some cells impassable
            HexBoard<String> board = HexBoard.hexagonal(4, "plains");

// Movement range of 2 steps from origin, avoiding walls
            Set<Hex> reachable = board.reachable(
                    Hex.ORIGIN, 2,
                    h -> !"wall".equals(board.get(h))
            );

// Draw a line from origin to a far corner
            List<Hex> line = Hex.ORIGIN.lineTo(Hex.of(2, -3));
        }
    }
}