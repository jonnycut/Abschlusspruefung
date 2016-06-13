import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by KNapret on 08.06.2016.
 */
public class Auto implements Comparable<Auto> , Comparator<Auto> {
    private String modellMarke;
    private String typ;

    public String getModellMarke() {
        return modellMarke;
    }

    public String getTyp() {
        return typ;
    }

    public int getkW() {
        return kW;
    }

    public String getSchaltung() {
        return schaltung;
    }

    public int getKm() {
        return km;
    }

    public String getErstZulassung() {
        return erstZulassung;
    }

    public int getPreis() {
        return preis;
    }

    private int kW;
    private String schaltung;
    private int km;
    private String erstZulassung;
    private int preis;

    public Auto(TreeMap<String, String> eigenschaften){

        this.modellMarke = eigenschaften.get("modellMarke");
        this.typ = eigenschaften.get("typ");

        if(!eigenschaften.get("preis").equals("N/A"))
            this.preis = Integer.parseInt(eigenschaften.get("preis").replaceAll("\\.",""));

        if(!eigenschaften.get("kW").equals("N/A"))
            this.kW =Integer.parseInt(eigenschaften.get("kW").replaceAll("\\.",""));

        this.schaltung = eigenschaften.get("schaltung");

        if(!eigenschaften.get("km").equals("N/A"))
            this.km = Integer.parseInt(eigenschaften.get("km").replaceAll("\\.",""));
        this.erstZulassung = eigenschaften.get("erstZulassung");
        



    }


    @Override
    public String toString() {
        return "Auto{" +
                "modellMarke='" + modellMarke + '\'' +
                ", typ='" + typ + '\'' +
                ", kW=" + kW +
                ", schaltung='" + schaltung + '\'' +
                ", km=" + km +
                ", erstZulassung='" + erstZulassung + '\'' +
                ", preis=" + preis +
                '}';
    }

    @Override
    public int compareTo(Auto a2) {

        return this.modellMarke.compareTo(a2.getModellMarke());
    }

    @Override
    public int compare(Auto a1, Auto a2) {
        return a1.compareTo(a2);
    }

    public static final Comparator<Auto> kW_ORDER = new Comparator<Auto>() {
        @Override
        public int compare(Auto a1, Auto a2) {
            return a1.getkW()-a2.getkW();
        }
    };

    public static final Comparator<Auto> kW_ORDER_DESC = new Comparator<Auto>() {
        @Override
        public int compare(Auto a1, Auto a2) {
            return a2.getkW()-a1.getkW();
        }
    };



    public static final Comparator<Auto> KM_ORDER = new Comparator<Auto>() {
        @Override
        public int compare(Auto a1, Auto a2) {

            return a1.getKm()-a2.getKm();
        }
    };

    public static final Comparator<Auto> KM_ORDER_DESC = new Comparator<Auto>() {
        @Override
        public int compare(Auto a1, Auto a2) {

            return a2.getKm()-a1.getKm();
        }
    };

    public  static final Comparator<Auto> PREIS_ORDER = new Comparator<Auto>() {
        @Override
        public int compare(Auto a1, Auto a2) {
            return a1.getPreis()-a2.getPreis() ;
        }
    };

    public  static final Comparator<Auto> PREIS_ORDER_DESC = new Comparator<Auto>() {
        @Override
        public int compare(Auto a1, Auto a2) {
            return a2.getPreis()-a1.getPreis() ;
        }
    };

    public static final Comparator<Auto> DESC = new Comparator<Auto>() {
        @Override
        public int compare(Auto a1, Auto a2) {
            return a2.getModellMarke().compareTo(a1.getModellMarke());
        }
    };

    public void setPreis(int preis) {
        this.preis=preis;

    }
}
