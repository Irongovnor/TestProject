package ordination;

import java.util.ArrayList;

public class Patient {
    private String cprnr;
    private String navn;
    private double vaegt;
    private final ArrayList<Ordination>ordinations=new ArrayList<>();
    // TODO: Link til Ordination
    public Patient(String cprnr, String navn, double vaegt) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaegt = vaegt;
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVaegt(){
        return vaegt;
    }

    public void setVaegt(double vaegt){
        this.vaegt = vaegt;
    }

    //TODO: Metoder (med specifikation) til at vedligeholde link til Ordination
    public void addOrdination(Ordination ordination){
        if(!ordinations.contains(ordination)){
            ordinations.add(ordination);
        }
    }

    public void removeOrdination(Ordination ordination){
        if(ordinations.contains(ordination)){
            ordinations.remove(ordination);
        }
    }
    @Override
    public String toString(){
        return navn + "  " + cprnr;
    }

}
