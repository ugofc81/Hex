package main.java.hex;

import java.util.*;

/**
 * Represents a single main.java.hex cell using cubic coordinates.
 * Invariant: q + r + s == 0
 */
public record Hex(int q, int r, int s) implements Comparable<Hex> {

    // ── Construction ──────────────────────────────────────────────────────────

    public Hex {
        if (q + r + s != 0)
            throw new IllegalArgumentException(
                    "Cubic coordinates must satisfy q + r + s = 0, got: %d+%d+%d=%d"
                            .formatted(q, r, s, q + r + s));
    }

    /** Convenience factory – derives s automatically. */
    public static Hex of(int q, int r) {
        return new Hex(q, r, -q - r);
    }

    public static final Hex ORIGIN = Hex.of(0, 0);

    @Override
    public int compareTo(Hex o) {
        if (this.q != o.q) {
            return o.q - this.q;
        }
        if (this.r != o.r) {
            return o.r - this.r;
        }
        return o.s - this.s;
    }

    // ── Directions & neighbours ───────────────────────────────────────────────

    /** The six unit-step directions in cubic space, ordered 0–5 clockwise from East. */
    public enum Direction {
        E  ( 1, -1,  0),
        SE ( 1,  0, -1),
        SW ( 0,  1, -1),
        W  (-1,  1,  0),
        NW (-1,  0,  1),
        NE ( 0, -1,  1);

        public final Hex delta;
        Direction(int q, int r, int s) { this.delta = new Hex(q, r, s); }
    }

    /** Returns the neighbour one step in the given direction. */
    public Hex neighbor(Direction d) { return add(d.delta); }

    /** Returns all six neighbours. */
    public List<Hex> neighbors() {
        return Arrays.stream(Direction.values())
                .map(this::neighbor)
                .toList();
    }

    // ── Arithmetic ────────────────────────────────────────────────────────────

    public Hex add(Hex o)      { return new Hex(q+o.q, r+o.r, s+o.s); }
    public Hex subtract(Hex o) { return new Hex(q-o.q, r-o.r, s-o.s); }
    public Hex transpose() { return new Hex(-s, -r, -q); }
    public Hex scale(int k)    { return new Hex(q*k,   r*k,   s*k);   }
    public Hex negate()        { return scale(-1); }
    public int module() {
        return Math.max(Math.max(Math.abs(q), Math.abs(r)), Math.abs(s));
    }

    // ── Rotation (around origin) ──────────────────────────────────────────────

    /** 60° clockwise rotation around the origin. */
    public Hex rotateCW()  { return new Hex(-r, -s, -q); }

    /** 60° counter-clockwise rotation around the origin. */
    public Hex rotateCCW() { return new Hex(-s, -q, -r); }

    // ── Distance & line-of-sight ──────────────────────────────────────────────

    /** main.java.hex.Hex-grid distance (number of steps) to another cell. */
    public int distanceTo(Hex o) {
        Hex d = subtract(o);
        return (Math.abs(d.q) + Math.abs(d.r) + Math.abs(d.s)) / 2;
    }

    /** Straight-line sequence of hexes from this cell to target (inclusive). */
    public List<Hex> lineTo(Hex target) {
        int n = distanceTo(target);
        if (n == 0) return List.of(this);
        List<Hex> line = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            double t = (double) i / n;
            line.add(lerpRound(this, target, t));
        }
        return Collections.unmodifiableList(line);
    }



    // ── Range queries ─────────────────────────────────────────────────────────

    /** All hexes within Manhattan-main.java.hex distance {@code radius} of this cell. */
    public List<Hex> range(int radius) {
        List<Hex> results = new ArrayList<>();
        for (int dq = -radius; dq <= radius; dq++) {
            int rMin = Math.max(-radius, -dq - radius);
            int rMax = Math.min( radius, -dq + radius);
            for (int dr = rMin; dr <= rMax; dr++){
                results.add(add(Hex.of(dq, dr)));
            }
        }
        return Collections.unmodifiableList(results);
    }

    /** The ring of hexes at exactly {@code radius} steps from this cell. */
    public List<Hex> ring(int radius) {
        if (radius == 0) return List.of(this);
        List<Hex> results = new ArrayList<>(6 * radius);
        Hex cursor = add(Direction.W.delta.scale(radius));
        for (Direction d : Direction.values())
            for (int i = 0; i < radius; i++) {
                results.add(cursor);
                cursor = cursor.neighbor(d);
            }
        return Collections.unmodifiableList(results);
    }

    public boolean borders(Hex h) {
        int dq = this.q - h.q;
        int dr = this.r - h.r;
        int ds = this.s - h.s;

        return ((dq == 0 || dr == 0 || ds == 0) && Math.max(Math.abs(dq), Math.max(Math.abs(dr), Math.abs(ds))) == 1);
    }

    public boolean isInList(List<Hex> list) {
        for (Hex hex : list) {
            if (this.equals(hex)) {
                return true;
            }
        }
        return false;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static Hex lerpRound(Hex a, Hex b, double t) {
        double fq = a.q + (b.q - a.q) * t;
        double fr = a.r + (b.r - a.r) * t;
        double fs = a.s + (b.s - a.s) * t;
        return roundCube(fq, fr, fs);
    }

    /** Rounds fractional cubic coordinates to the nearest valid main.java.hex. */
    public static Hex roundCube(double fq, double fr, double fs) {
        long q = Math.round(fq), r = Math.round(fr), s = Math.round(fs);
        double dq = Math.abs(q - fq), dr = Math.abs(r - fr), ds = Math.abs(s - fs);
        if (dq > dr && dq > ds) q = -r - s;
        else if (dr > ds)       r = -q - s;
        else                    s = -q - r;
        return new Hex((int) q, (int) r, (int) s);
    }

    /**
     * Partitions a collection of hexes into topologically separate groups,
     * where two hexes belong to the same group if they are connected
     * transitively through shared edges.
     *
     * @param hexes the hexes to partition
     * @return a list of connected components, each as an unmodifiable set
     */
    public static List<Set<Hex>> connectedComponents(Collection<Hex> hexes) {
        Set<Hex> unvisited = new HashSet<>(hexes);
        List<Set<Hex>> components = new ArrayList<>();

        while (!unvisited.isEmpty()) {
            Hex seed = unvisited.iterator().next();

            Set<Hex> component = new HashSet<>();
            Deque<Hex> queue = new ArrayDeque<>();
            queue.add(seed);
            component.add(seed);

            while (!queue.isEmpty()) {
                Hex cur = queue.poll();
                for (Hex nb : cur.neighbors()) {
                    if (unvisited.contains(nb) && component.add(nb))
                        queue.add(nb);
                }
            }

            unvisited.removeAll(component);
            components.add(Collections.unmodifiableSet(component));
        }

        return Collections.unmodifiableList(components);
    }
}