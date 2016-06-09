import java.util.Map;

/**
 * Created by KNapret on 08.06.2016.
 */
public class Auto implements Comparable<Auto>  {
    private String modellMarke;
    private String typ;
    private int kW;
    private String schaltung;
    private int km;
    private String erstZulassung;
    private int preis;

    public Auto(Map<String, String> eigenschaften){

        this.modellMarke = eigenschaften.get("modellMarke");
        this.typ = eigenschaften.get("typ");
        this.preis = Integer.parseInt(eigenschaften.get("preis"));
        this.kW =Integer.parseInt(eigenschaften.get("kW"));
        this.schaltung = eigenschaften.get("schaltung");
        this.km = Integer.parseInt(eigenschaften.get("km"));
        this.erstZulassung = eigenschaften.get("erstZulassung");
        



    }










    @Override
    public int compareTo(Auto o) {
        return 0;
    }
}
