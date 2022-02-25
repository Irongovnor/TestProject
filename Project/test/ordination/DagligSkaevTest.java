package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    @Test
    void samletDosis() {
        //TC2
        // kun 1 tidspunkt
        Laegemiddel laegemiddel=new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient=new Patient("121256-0512", "Jane Jensen", 63.4);
        LocalTime[] kl = { LocalTime.of(12, 0)};
        double[] an = {2};

        assertEquals(4, Controller.getController().opretDagligSkaevOrdination(LocalDate.of(2021, 1, 23),
                LocalDate.of(2021, 1, 24), patient,
                laegemiddel, kl, an).samletDosis());
    }

    @Test
    void doegnDosis() {
        //TC2
        //kun 1 tidspunkt
        Laegemiddel laegemiddel=new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient=new Patient("121256-0512", "Jane Jensen", 63.4);
        LocalTime[] kl = { LocalTime.of(12, 0)};
        double[] an = {2};

        assertEquals(2, Controller.getController().opretDagligSkaevOrdination(LocalDate.of(2021, 1, 23),
                LocalDate.of(2021, 1, 24), patient,
                laegemiddel, kl, an).doegnDosis());


        //TC4
        // kl er 23:59 for at se om det virker da det er en ækvivalens punkt
        LocalTime[] kl2 = { LocalTime.of(23, 59)};
        assertEquals(2, Controller.getController().opretDagligSkaevOrdination(LocalDate.of(2021, 1, 23),
                LocalDate.of(2021, 1, 24), patient,
                laegemiddel, kl2, an).doegnDosis());

        //TC11
        // her er dosis kun 0.1
        double[] an2 = {0.1};
        assertEquals(0.1, Controller.getController().opretDagligSkaevOrdination(LocalDate.of(2021, 1, 23),
                LocalDate.of(2021, 1, 24), patient,
                laegemiddel, kl, an2).doegnDosis());
    }
    }
