package main.java.hex;

import java.util.*;
import java.util.function.Predicate;

/**
 * A finite hexagonal board that maps main.java.hex.Hex coordinates to tile values.
 *
 * @param <T> the type of value stored in each cell (use Void / null for presence-only boards)
 */
public class HexBoard<T> {

    private final Map<Hex, T> cells;

    // ── Construction ──────────────────────────────────────────────────────────

    public HexBoard() { this.cells = new HashMap<>(); }

    /** Copy constructor. */
    public HexBoard(HexBoard<T> other) { this.cells = new HashMap<>(other.cells); }

    // ── Standard board shapes ─────────────────────────────────────────────────

    /** Creates a filled regular hexagon of given radius (0 = single cell). */
    public static <T> HexBoard<T> hexagonal(int radius, T fillValue) {
        HexBoard<T> board = new HexBoard<>();
        System.out.println(board);
        Hex.ORIGIN.range(radius).forEach(h -> board.set(h, fillValue));
        return board;
    }

    /** Creates a parallelogram board with q in [0, width) and r in [0, height). */
    public static <T> HexBoard<T> parallelogram(int width, int height, T fillValue) {
        HexBoard<T> board = new HexBoard<>();
        for (int q = 0; q < width;  q++)
            for (int r = 0; r < height; r++)
                board.set(Hex.of(q, r), fillValue);
        return board;
    }

    // ── Cell access ───────────────────────────────────────────────────────────

    public void set(Hex hex, T value)         { cells.put(hex, value); }
    public T    get(Hex hex)                  { return cells.get(hex); }
    public boolean contains(Hex hex)          { return cells.containsKey(hex); }
    public void remove(Hex hex)               { cells.remove(hex); }
    public Set<Hex> hexes()                   { return Collections.unmodifiableSet(cells.keySet()); }
    public int size()                         { return cells.size(); }

    /** Returns all occupied neighbours of {@code main.java.hex} that are on the board. */
    public List<Hex> neighbors(Hex hex) {
        return hex.neighbors().stream()
                .filter(this::contains)
                .toList();
    }

    // ── Flood fill / reachability ─────────────────────────────────────────────

    /**
     * BFS from {@code start}; visits only cells accepted by {@code passable}.
     *
     * @return all reachable hexes (including start), in BFS order
     */
    public List<Hex> floodFill(Hex start, Predicate<Hex> passable) {
        if (!contains(start) || !passable.test(start)) return List.of();
        List<Hex> visited = new ArrayList<>();
        Set<Hex>  seen    = new HashSet<>();
        Deque<Hex> queue  = new ArrayDeque<>();
        queue.add(start);
        seen.add(start);
        while (!queue.isEmpty()) {
            Hex cur = queue.poll();
            visited.add(cur);
            for (Hex nb : neighbors(cur))
                if (passable.test(nb) && seen.add(nb))
                    queue.add(nb);
        }
        return Collections.unmodifiableList(visited);
    }

    /**
     * BFS movement range: all hexes reachable from {@code start}
     * within {@code moves} steps, subject to the passable predicate.
     */
    public Set<Hex> reachable(Hex start, int moves, Predicate<Hex> passable) {
        Map<Hex, Integer> cost = new HashMap<>();
        cost.put(start, 0);
        Deque<Hex> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Hex cur = queue.poll();
            int c   = cost.get(cur);
            if (c >= moves) continue;
            for (Hex nb : neighbors(cur))
                if (passable.test(nb) && !cost.containsKey(nb)) {
                    cost.put(nb, c + 1);
                    queue.add(nb);
                }
        }
        return Collections.unmodifiableSet(cost.keySet());
    }

    @Override
    public String toString() {
        return "main.java.hex.HexBoard{size=" + size() + ", hexes=" + cells.keySet() + "}";
    }
}