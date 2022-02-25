package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void samletDosis() {
        //TC1
        // 6 doser pr døgn
        Laegemiddel laegemiddel=new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient=new Patient("121256-0512", "Jane Jensen", 63.4);
        DagligFast dagligsfast=new DagligFast(LocalDate.of(2022, 2, 14),
                LocalDate.of(2022, 2, 16),patient ,laegemiddel
                , 2, 2,2,0);
        assertEquals(18, dagligsfast.samletDosis());
    }

    @Test
    void doegnDosis() {
        //TC1
        // 6 doser pr døgn
        Laegemiddel laegemiddel=new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient=new Patient("121256-0512", "Jane Jensen", 63.4);
        DagligFast dagligsfast=new DagligFast(LocalDate.of(2022, 2, 14),
                LocalDate.of(2022, 2, 16),patient ,laegemiddel
                , 2, 2,2,0);
        assertEquals(6, dagligsfast.doegnDosis());
        //TC2
        // 0,1 doser pr døgn
        DagligFast dagligsfastækvivalens=new DagligFast(LocalDate.of(2022, 2, 14),
                LocalDate.of(2022, 2, 16),patient ,laegemiddel
                , 0.1, 0,0,0);
        assertEquals(0.100, dagligsfastækvivalens.doegnDosis(),0.00000001);
    }
}