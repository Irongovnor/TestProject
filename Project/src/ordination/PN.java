package ordination;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PN extends Ordination {

    private double antalEnheder;

    private final Map<LocalDate, Integer> ordinationer = new HashMap<>();

    public PN(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel) {
        super(startDen, slutDen, patient, laegemiddel);
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
            if (ordinationer.get(givesDen) == null) {
                ordinationer.put(givesDen, 1);
            } else {
                ordinationer.put(givesDen, ordinationer.get(givesDen) + 1);
            }
            doneringGivet = true;
        }
        return doneringGivet;
    }

    public double doegnDosis() {
        return samletDosis() / getAntalGangeGivet();
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
        for (Integer antal : ordinationer.values()){
            samletGivet += antal;
        }
        return samletGivet;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
