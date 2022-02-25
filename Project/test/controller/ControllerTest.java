package controller;

import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

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
    }
}