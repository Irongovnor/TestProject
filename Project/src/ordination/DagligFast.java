package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class DagligFast extends Ordination {
    private final Dosis[]doses=new Dosis[4];
    private double morgenAntal;
    private double middagAntal;
    private double aftenAntal;
    private double natAntal;
    public DagligFast(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel, double morgenAntal,double middagAntal,double aftenAntal,double natAntal) {
        super(startDen, slutDen, patient, laegemiddel);
        doses[0]=new Dosis(LocalTime.of(8, 00), morgenAntal);
        doses[1]=new Dosis(LocalTime.of(12, 00), middagAntal);
        doses[2]=new Dosis(LocalTime.of(18, 00), aftenAntal);
        doses[3]=new Dosis(LocalTime.of(23, 00), natAntal);
    }
    @Override
    public double samletDosis() {
        double sum=0;
        for (Dosis dose : doses) {
            sum += dose.getAntal();
        }
        return sum;
    }

    @Override
    public double doegnDosis() {
        double sum=0;
        for (Dosis dose : doses) {
            sum += dose.getAntal();
        }
        return sum/antalDage();
    }

    @Override
    public String getType() {
        return getLaegemiddel().getNavn();
    }

    public Dosis[] getDoser() {
        return doses;
    }
}
