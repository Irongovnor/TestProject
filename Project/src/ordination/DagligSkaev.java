package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{
    private LocalTime[] tider;
    private double[] antalende;
    ArrayList<Dosis> doses=new ArrayList<>();
    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel,LocalTime[] tid,double[] antal) {
        super(startDen, slutDen, patient, laegemiddel);
        this.antalende=antal;
        this.tider=tid;
        for (int i=0;i<tid.length;i++){
            opretDosis(tid[i],antal[i]);
        }
    }
    // TODO

    public void opretDosis(LocalTime tid, double antal) {
        Dosis d=new Dosis(tid, antal);
            doses.add(d);
    }

    @Override
    public double samletDosis() {
        double sum=0;
        for (Dosis dose : doses) {
            sum+=dose.getAntal()*(ChronoUnit.DAYS.between(getStartDen(),getSlutDen())+1);
        }
        return sum;
    }

    @Override
    public double doegnDosis() {
        double sum=0;
        for (Dosis dose : doses) {
            sum+=dose.getAntal()*(ChronoUnit.DAYS.between(getStartDen(),getSlutDen())+1);
        }
        return sum/antalDage();
    }

    @Override
    public String getType() {
        return getLaegemiddel().getNavn();
    }

    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(doses);
    }
}
