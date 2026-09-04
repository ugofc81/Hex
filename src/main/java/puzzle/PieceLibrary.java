package main.java.puzzle;

import main.java.hex.Hex;

import java.util.List;

public class PieceLibrary {
    public static final List<Hex> listA = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(2,-1),
		Hex.of(1,1)
	);
    public static boolean listATransposable = false;

    public static final List<Hex> listB = List.of(
		Hex.of(0,0),
		Hex.of(0,-1),
		Hex.of(0,-2),
		Hex.of(-1,1),
		Hex.of(-2,2)
	);
    public static boolean listBTransposable = false;

    public static final List<Hex> listC = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(0,1),
		Hex.of(0,2)
	);
    public static boolean listCTransposable = true;

    public static final List<Hex> listD = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(0,-1),
		Hex.of(0,-2)
	);
    public static boolean listDTransposable = true;

    public static final List<Hex> listE = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(1,-1),
		Hex.of(2,-1)
	);
    public static boolean listETransposable = true;

    public static final List<Hex> listG = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(-1,1),
		Hex.of(-2,1)
	);
	public static boolean listGTransposable = true;

	public static final List<Hex> listH = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(1,1),
		Hex.of(0,-1)
	);
	public static boolean listHTransposable = true;

	public static final List<Hex> listI = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(0,1),
		Hex.of(-1,-1)
	);
	public static boolean listITransposable = true;

	public static final List<Hex> listJ = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(2,0),
		Hex.of(-2,1)
	);
	public static boolean listJTransposable = true;

	public static final List<Hex> listK = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(2,-1),
		Hex.of(3,-1)
	);
	public static boolean listKTransposable = true;

	public static final List<Hex> listL = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(2,0),
		Hex.of(1,1)
	);
	public static boolean listLTransposable = true;

	public static final List<Hex> listM = List.of(
		Hex.of(0,0),
		Hex.of(-1,0),
		Hex.of(1,0),
		Hex.of(1,-1),
		Hex.of(-1,-1)
	);
	public static boolean listMTransposable = true;
}
