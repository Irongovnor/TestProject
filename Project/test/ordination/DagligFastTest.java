package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.awt.*;
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
        //TC2
        // 0,1 doser pr døgn
        DagligFast dagligsfastækvivalens=new DagligFast(LocalDate.of(2022, 2, 14),
                LocalDate.of(2022, 2, 16),patient ,laegemiddel
                , 0.1, 0,0,0);
        assertEquals(0.300, dagligsfastækvivalens.samletDosis(),0.00000001);
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
    @Test
    void doegnDosis1() {
        //TC7
        // datoen er 16 hvor statdatoen er 14
        Laegemiddel laegemiddel=new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient=new Patient("121256-0512", "Jane Jensen", 63.4);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            Controller.getController().opretDagligFastOrdination(LocalDate.of(2022, 2, 16),
                    LocalDate.of(2022, 2, 14),patient ,laegemiddel
                    , 2, 2,2,0);
        });
        assertEquals("Slutperioden ligger uden for den valgte startperioden",exception.getMessage());
        //TC14
        //alt er bare 0 men den bliver oprettet aligevel
        DagligFast dagligsfast=new DagligFast(LocalDate.of(2022, 2, 14),
                LocalDate.of(2022, 2, 16),patient ,laegemiddel
                , 000, 000,00,0);

        assertEquals(0,dagligsfast.doegnDosis());
    }
}