package controller;

import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller;
    @BeforeEach
    void setUp() {
        controller=Controller.getTestController();

    }
    @Test
    void ordinationPNAnvendt() {
        //TC17
        //ser om hvis man giver en dosis uden for dato
        Laegemiddel laegemiddel=new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient=new Patient("121256-0512", "Jane Jensen", 63.4);
        PN pn=new PN(LocalDate.of(2022, 2, 14),
                LocalDate.of(2022, 2, 16),patient ,laegemiddel
                , 2);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Controller.getController().ordinationPNAnvendt(pn, LocalDate.of(2022, 2, 17));
        });
        assertEquals("Datoen ligger uden for den tilladte doseringsperiode",exception.getMessage());
    }

    @Test
    void anbefaletDosisPrDoegn() {
        //TC18
        //nomal vægt
        Laegemiddel laegemiddel=new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient=new Patient("121256-0512", "Jane Jensen", 63.4);
        assertEquals(0.15, Controller.getController().anbefaletDosisPrDoegn(patient, laegemiddel));
        //TC19
        //lav vægt
        Patient patient2=new Patient("121256-0512", "Jane Jensen", 20.5);
        assertEquals(0.1, Controller.getController().anbefaletDosisPrDoegn(patient2, laegemiddel));
        //TC 20
        //høj vægt
        Patient patient3=new Patient("121256-0512", "Jane Jensen", 130);
        assertEquals(0.16, Controller.getController().anbefaletDosisPrDoegn(patient3, laegemiddel));

    }
    @Test
    void anbefaletDosisPrDoegn1(){
        //TC21
        // test på ækvivalens med lav vægt
        Laegemiddel laegemiddel=new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient=new Patient("121256-0512", "Jane Jensen", 25.9);
        assertEquals(0.1, Controller.getController().anbefaletDosisPrDoegn(patient, laegemiddel));
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
        Patient patient= controller.opretPatient("121256-0512", "Jane Jensen", 63.4);
        Patient patient2= controller.opretPatient("070985-1153", "Finn Madsen", 83.2);
        Patient patient3= controller.opretPatient("050972-1233", "Hans Jørgensen", 89.4);
        Patient patient4= controller.opretPatient("011064-1522", "Ulla Nielsen", 59.9);
        Patient patient5= controller.opretPatient("090149-2529", "Ib Hansen", 87.7);

        Laegemiddel laegemiddel=  controller.opretLaegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Laegemiddel laegemiddel2= controller.opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        Laegemiddel laegemiddel3= controller.opretLaegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
        Laegemiddel laegemiddel4=  controller.opretLaegemiddel("Methotrexat", 0.01, 0.015, 0.02, "Styk");

        controller.opretPNOrdination(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 12),
                patient, laegemiddel2,
                123);

        controller.opretPNOrdination(LocalDate.of(2021, 2, 12), LocalDate.of(2021, 2, 14),
                patient, laegemiddel2,
                3);

        controller.opretPNOrdination(LocalDate.of(2021, 1, 20), LocalDate.of(2021, 1, 25),
                patient4, laegemiddel3,
                5);

        controller.opretPNOrdination(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 12),
                patient, laegemiddel2,
                123);

        controller.opretDagligFastOrdination(LocalDate.of(2021, 1, 10),
                LocalDate.of(2021, 1, 12), patient2,
                laegemiddel2, 2, 0, 1, 0);

        LocalTime[] kl = { LocalTime.of(12, 0), LocalTime.of(12, 40),
                LocalTime.of(16, 0), LocalTime.of(18, 45) };
        double[] an = { 0.5, 1, 2.5, 3 };

        controller.opretDagligSkaevOrdination(LocalDate.of(2021, 1, 23),
                LocalDate.of(2021, 1, 24), patient2,
                laegemiddel3, kl, an);
        assertEquals(9,controller.antalOrdinationerPrVægtPrLægemiddel(20, 100, laegemiddel2));
        assertEquals(14,controller.antalOrdinationerPrVægtPrLægemiddel(20, 100, laegemiddel3));
        assertEquals(0,controller.antalOrdinationerPrVægtPrLægemiddel(20, 100, laegemiddel4));
    }



}