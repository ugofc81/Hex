package test.java;

import main.java.hex.Hex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HexTest {

    // ── Construction ──────────────────────────────────────────────────────────

    @Test
    void constructorRejectsInvalidCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> new Hex(1, 1, 1));
    }

    @Test
    void factoryDerivesS() {
        Hex h = Hex.of(2, -1);
        assertEquals(-1, h.s());
    }

    // ── Range ─────────────────────────────────────────────────────────────────

    @ParameterizedTest
    @CsvSource({"0,1", "1,7", "2,19", "3,37", "4,61"})
    void rangeHasCorrectCellCount(int radius, int expected) {
        assertEquals(expected, Hex.ORIGIN.range(radius).size());
    }

    @Test
    void rangeContainsOnlyValidHexes() {
        Hex.ORIGIN.range(3).forEach(h ->
                assertEquals(0, h.q() + h.r() + h.s(), "invariant broken for " + h));
    }

    // ── Neighbours ────────────────────────────────────────────────────────────

    @Test
    void neighborThenOppositeReturnsOrigin() {
        for (Hex.Direction d : Hex.Direction.values()) {
            Hex there = Hex.ORIGIN.neighbor(d);
            // find opposite direction
            Hex back = there.neighbor(Hex.Direction.values()[(d.ordinal() + 3) % 6]);
            assertEquals(Hex.ORIGIN, back, "failed for direction " + d);
        }
    }

    // ── Connected components ──────────────────────────────────────────────────

    @Test
    void singleHexIsOneComponent() {
        assertEquals(1, Hex.connectedComponents(List.of(Hex.ORIGIN)).size());
    }

    @Test
    void adjacentHexesAreOneComponent() {
        List<Hex> hexes = List.of(Hex.of(0, 0), Hex.of(1, 0));
        assertEquals(1, Hex.connectedComponents(hexes).size());
    }

    @Test
    void disjointHexesAreTwoComponents() {
        List<Hex> hexes = List.of(Hex.of(0, 0), Hex.of(5, 0));
        assertEquals(2, Hex.connectedComponents(hexes).size());
    }
}