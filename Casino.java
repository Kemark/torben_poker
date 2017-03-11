
/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 13.02.2017
  * @author 
  */
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.Calendar;

public class Casino {

  private String Passwort;
  private String Name;
  private int Kontostand;
  private int Einsatz;

  // array enthaelt alle karten
  private ArrayList<Karte> kartenListe = new ArrayList<Karte>();

  // feld enthaelt die karten rueckseite
  private ImageIcon kartenrueckseite;

  private DBZugriffCasino meinDBZugriff = new DBZugriffCasino();

  public String getPasswort() {
    return Passwort;
  }

  public void setPasswort(String Passwort) {
    this.Passwort = Passwort;
  }

  public String getName() {
    return Name;
  }

  public void setName(String Name) {
    this.Name = Name;
  }

  public int getKontostand() {
    return Kontostand;
  }

  public void setKontostand(int Kontostand) {
    this.Kontostand = Kontostand;
  }

  public int getEinsatz() {
    return Einsatz;
  }

  public void setEinsatz(int Einsatz) {
    this.Einsatz = Einsatz;
  }

  private ArrayList<String> karten; //ArrayList 

  public Casino() {
    karten = new ArrayList<String>();
  }

  public void setKarten(String pKarten) {
    karten.add(pKarten);
  }

  public String getKarten(int pIndex) {
    return karten.get(pIndex);
  }

  public int getSizeKarten() {
    return karten.size();
  }

  public void registrieren(String name, String passwort, String kontostand) {

    this.setName(name);
    this.setPasswort(passwort);
    this.setKontostand(Integer.parseInt(kontostand));
    //meinDBZugriff.erfasseKunde(this);
  }

  public void anmelden(String name, String passwort, JTextField tfKontostand, JTextField tfEinsatz) {

    this.setName(name);
    this.setPasswort(passwort);
    //meinDBZugriff.sucheKunde(poker);
    tfKontostand.setText(String.valueOf(this.getKontostand()));
    tfEinsatz.setText(Integer.toString(this.getEinsatz()));
  }

  public void aendern(String name, String kontostand, String einsatz) {

    int rc = 0;
    this.setName(name);
    //meinDBZugriff.sucheKunde(this);
    this.setKontostand(Integer.parseInt(kontostand));
    this.setEinsatz(Integer.parseInt(einsatz));
    //meinDBZugriff.aendereKunde(this);

    if (rc < 0) {
      JOptionPane.showMessageDialog(null, "Zugriff auf DB fehlgeschlagen", "Fehler", JOptionPane.INFORMATION_MESSAGE);
    } // end of if    
  }
  /**
   * liefert eine Zufallszahl
   */
  private int createRandomNumber(int low, int high) {

    // Berechnung der Zufallszahl laut Beschreibung
    return (int) (Math.random() * (high - low + 1) + low);
  }

  public ImageIcon getKarteZufaellig() {

    // Zufallszahl generieren (laut Beschreibung)
    int x = this.createRandomNumber(1, this.kartenListe.size());

    // index des arrays beginnt bei 0, daher 1 abziehen.
    return this.kartenListe.get(x-1).getImage();
  }

  // liefert die Kartenrueckseite
  public ImageIcon getKarteRueckseite() {
    return this.kartenrueckseite;
  }

  /**
   * alle Karten (png) im Verzeichnis images laden
   */
  public void ladeKarten(int imageWidth, int imageHeigth) {

    File dir = new File("Karten");
    // alle Daten im Verzeichnis "Karten"" geben lassen
    File[] kartenFiles = dir.listFiles();

    if (kartenFiles == null) {
      // prufen, ob das Verzeichnis existiert.
      throw new RuntimeException("keine Karten gefunden");
    }

    // ueber alle Dateien (Karten) im Verzeichns "Karten" loopen.
    for (File kartenFile : kartenFiles) {

      // nur Dateien mit der Endung .png laden (alle anderen Dateiendungen herausfiltern)
      if(!kartenFile.getName().endsWith(".png")) {
        continue;
      }      

      // Datei als ImageIcon in den Speicher laden.
      ImageIcon image = new ImageIcon(kartenFile.getAbsolutePath());

      // Karten an die vorgegeben groesse anpassen (skalieren)
      ImageIcon angpepasstesImage = new ImageIcon(image.getImage().getScaledInstance(imageWidth, imageHeigth, Image.SCALE_SMOOTH));
      String kartenFileName = kartenFile.getName();
      if(kartenFileName.equals("Rueck.png") ) {
        
        this.kartenrueckseite = angpepasstesImage;
      } 
      else {

        //siehe: http://stackoverflow.com/questions/14833008/java-string-split-with-dot
        String[] kartenInfo = kartenFileName.split("\\.");
        Karte karte;
        switch(kartenInfo.length) {
            case 1:
            case 2:
              break;
            case 3:
              karte = new Karte(kartenInfo[0], Integer.parseInt(kartenInfo[1]), kartenInfo[1], angpepasstesImage);
              this.kartenListe.add(karte);
              break;
            case 4:
              karte = new Karte(kartenInfo[0], Integer.parseInt(kartenInfo[2]), kartenInfo[1], angpepasstesImage);            
              this.kartenListe.add(karte);
              break;
        }
        // Karten Image in array list speichern
      }
    }
  }

  /*Calendar cal = Calendar.getInstance();
    System.out.println("Datum:"+cal.get(Calendar.DAY_OF_MONTH)+"."+;
    (cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR));
    System.out.println("Uhrzeit:"+cal.get(Calendar.HOUR_OF_DAY)+":"+;
    cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+":"+;
    cal.get(Calendar.MILLISECOND));   */
} // end of class Casino
