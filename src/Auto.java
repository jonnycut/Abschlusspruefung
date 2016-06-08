import java.util.List;
import java.util.Map;

/**
 * Created by KNapret on 08.06.2016.
 */
public class Auto implements Comparable<Auto>  {
    private String marke;
    private String modell;
    private String farbe;
    private int ps;
    private String schaltung;
    private int km;
    private String erstZulassung;


    public Auto(Map<String, String> eigenschaften){

        this.marke = eigenschaften.get("marke");
        this.modell = eigenschaften.get("modell");
        this.farbe=eigenschaften.get("farbe");
        this.ps=Integer.parseInt(eigenschaften.get("ps"));
        this.schaltung = eigenschaften.get("schaltung");
        this.km = Integer.parseInt(eigenschaften.get("km"));
        this.erstZulassung = eigenschaften.get("erstZulassung");
        



    }










    @Override
    public int compareTo(Auto o) {
        return 0;
    }
}
