package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PN extends Ordination {

    private double antalEnheder;

    private final Map<LocalDate, Integer> ordinationer = new TreeMap<>();

    public PN(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel, double antalEnheder) {
        super(startDen, slutDen, patient, laegemiddel);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     *
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean doneringGivet = false;
        if (givesDen.compareTo(getStartDen()) >= 0 && givesDen.compareTo(getSlutDen()) <= 0) {
            if (!ordinationer.containsKey(givesDen)) {
                ordinationer.put(givesDen, 1);
            } else {
                ordinationer.put(givesDen, ordinationer.get(givesDen) + 1);
            }
            doneringGivet = true;
        }
        return doneringGivet;
    }

    public double doegnDosis() {
        double dosis = 0;
        ArrayList<LocalDate> datoer = new ArrayList<>();
        for (LocalDate date : ordinationer.keySet()) {
            datoer.add(date);
        }
        if (datoer.size() > 0) {
            dosis = getAntalGangeGivet() * antalEnheder / (ChronoUnit.DAYS.between(datoer.get(0), datoer.get(datoer.size() - 1)) + 1);
        }
        return dosis;
    }

    /**
     * Returnerer ordinationstypen som en String
     *
     * @return
     */
    @Override
    public String getType() {
        return getLaegemiddel().getNavn();
    }


    public double samletDosis() {
        return getAntalGangeGivet() * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     *
     * @return
     */
    public int getAntalGangeGivet() {
        int samletGivet = 0;
        for (Integer antal : ordinationer.values()) {
            samletGivet += antal;
        }
        return samletGivet;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
