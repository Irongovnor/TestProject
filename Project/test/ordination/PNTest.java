package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {
    Laegemiddel laegemiddel = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
    Patient patient = new Patient("090149-2529", "Ib Hansen", 87.7);
    PN pn = new PN(LocalDate.of(2022, 2, 25), LocalDate.of(2022, 3, 2), patient, laegemiddel, 2);

    @BeforeEach
    void setUp() {
        pn.givDosis(LocalDate.of(2022, 2, 25));
        pn.givDosis(LocalDate.of(2022, 2, 28));
        pn.givDosis(LocalDate.of(2022, 3, 2));
    }

    @Test
    void givDosis() {
        // TC-13
        // 2 doser pr gang givet
        assertTrue(pn.givDosis(LocalDate.of(2022, 2, 25)));
        assertTrue(pn.givDosis(LocalDate.of(2022, 2, 28)));
        assertTrue(pn.givDosis(LocalDate.of(2022, 3, 2)));
    }

    @Test
    void doegnDosis() {
        //TC-3
        // 3 doser taget
        assertEquals(1, pn.doegnDosis());
        // 6 doser taget
        pn.givDosis(LocalDate.of(2022, 2, 28));
        pn.givDosis(LocalDate.of(2022, 2, 28));
        pn.givDosis(LocalDate.of(2022, 2, 28));
        assertEquals(2, pn.doegnDosis());
    }

    @Test
    void getType() {
        assertEquals("Fucidin", pn.getType());
    }

    @Test
    void samletDosis() {
        assertEquals(6,pn.samletDosis());
        pn.givDosis(LocalDate.of(2022, 2, 28));
        assertEquals(8,pn.samletDosis());
    }

    @Test
    void getAntalGangeGivet() {
        assertEquals(3,pn.getAntalGangeGivet());
        pn.givDosis(LocalDate.of(2022, 2, 28));
        assertEquals(4,pn.getAntalGangeGivet());
    }

    @Test
    void getAntalEnheder() {
        assertEquals(2,pn.getAntalEnheder());
    }
}