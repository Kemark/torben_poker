import java.util.Arrays;
import java.util.Comparator;

import jdk.internal.dynalink.linker.ConversionComparator;

/**
 * Klasse zur Gewinnermittlung
 */
public class Kombination {

    private Karte[] aktuelleKarten;

    /**
     * Konstruktor
     */
    public Kombination(Karte[] aktuelleKarten) {
        this.aktuelleKarten = aktuelleKarten;
    }
    public String getKombination() {

        // siehe https://docs.oracle.com/javase/tutorial/collections/interfaces/order.html
       Arrays.sort(this.aktuelleKarten, new Comparator<Karte>() {
            // sortiert zuerst anhand des wertes
            public int compare(Karte a, Karte b) {
                    if(a.getWert() == b.getWert()) {
                        return 0;
                    }
                    if (a.getWert() > b.getWert()) {
                        return 1;
                    }
                    return -1;
            }
        });

        // siehe https://www.tutorialspoint.com/java/java_string_compareto.htm
        Arrays.sort(this.aktuelleKarten, new Comparator<Karte>() {
            // sortiert zuerst anhand der Farbe
            public int compare(Karte a, Karte b) {
                return a.getFarbe().compareTo(b.getFarbe());
            }
        });

        for (int i=0; i<5; i++) {
            Karte karte = this.aktuelleKarten[i];
        }

        if(this.pruefeRoyalFlush()) {
            return "Royal Flush";
        }
        else if(this.pruefeStraightFlush()) {
            return "Straigth Flush";
        }
        else if(this.pruefeGleicheWerte(4)) {
            return "Vierling";
        }
        else if(this.pruefeFullHouse()) {
            return "Full House";
        }
        else if(this.pruefeFlush()) {
            return "Flush";
        }
        else if(this.pruefeStrasse()) {
            return "Strasse";
        }
        else if(this.pruefeGleicheWerte(3)) {
            return "Drilling";
        }
        else if(this.pruefeZweiPaare()) {
            return "Zwei Paare";
        }
        else if(this.pruefeGleicheWerte(2)) {
            return "Paar";
        }

        return "";
    }

    /**
     * prueft, ob die aktuellen Karten zwei Paare sind
     */
    private boolean pruefeZweiPaare() {
        int paare = 0;

        int[] werteListe = erzeugeWerteListe();
        for (int i=0; i<13; i++) {
            if(werteListe[i] == 2) {
                paare++;
            }
        }

        if(paare == 2) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * prueft, ob die aktuellen Karten ein Full House entsprechen
     */
    private boolean pruefeFullHouse() {
        boolean drilling = false;
        boolean zwilling = false;

        int[] werteListe = erzeugeWerteListe();
        for (int i=0; i<13; i++) {
            if(werteListe[i] == 3) {
                drilling = true;
            }
            if(werteListe[i] == 2) {
                zwilling = true;
            }
        }

        if(drilling == true && zwilling == true) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * prueft, ob die aktuellen Karten ein Royal Flush sind
     */
    private boolean pruefeRoyalFlush() {
        
        // pruefen auf gleiche Farbe
        if(!this.pruefeGleicheFarben()) {
            return false;
        }

        // pruefen, ob der kleinste Wert eine 10 ist.
        if(this.aktuelleKarten[0].getWert() != 10) {
            return false;
        }

        return true;
    }

    /**
     * prueft, ob die aktuellen Karten ein Straight Flush sind
     */
    private boolean pruefeStraightFlush() {

        // pruefen auf gleiche Farbe
        if(!this.pruefeGleicheFarben()) {
            return false;
        }

        // pruefen auf strasse
        if(!this.pruefeStrasse()) {
            return false;
        }

        return true;
    }

    /**
     * prueft, ob die aktuellen Karten ein Flush sind
     */
    private boolean pruefeFlush() {

        // pruefen auf gleiche Farbe
        if(!this.pruefeGleicheFarben()) {
            return false;
        }

        return true;
    }

    /**
     * prueft, ob die aktuellen Karten eine bestimmte Anzahl an gleichen Werten enthaelt
     */
    private boolean pruefeGleicheWerte(int wert) {

        int[] werteListe = erzeugeWerteListe();
        for (int i=0; i<13; i++) {
            if(werteListe[i] == wert) {
                return true;
            }
        }

        return false;
    }

    /**
     * erzeugt eine Array liste mit der Anzahl der gleichen Werten pro Wert. Der Wert entspricht dem
     * Eintrag in der Array Liste.
     */
    private int[] erzeugeWerteListe() {

        // siehe http://stackoverflow.com/questions/1938101/how-to-initialize-an-array-in-java        
        int[] werteListe = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i=0; i<5; i++) {
            werteListe[this.aktuelleKarten[i].getWert()-2]++;
        }

        return werteListe;
    }

    /**
     * prueft, ob die Karten eine Strasse ergeben
     */
    private boolean pruefeStrasse() {
        int wert = this.aktuelleKarten[0].getWert();
        for (int i=1; i<5; i++) {
            if(this.aktuelleKarten[i].getWert() != wert+1) {
                return false;
            }
            wert = this.aktuelleKarten[i].getWert();
        }

        return true;
    }

    /**
     * prueft, ob die Karten die gleiche Farben haben
     */
    private boolean pruefeGleicheFarben() {
        String farbe = this.aktuelleKarten[0].getFarbe();
        for (int i=1; i<5; i++) {
            Karte karte = this.aktuelleKarten[i];
            if(!karte.getFarbe().equals(farbe)) {
                return false;
            }
        }
        return true;
    }
}