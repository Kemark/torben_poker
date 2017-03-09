
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
  private ArrayList<ImageIcon> kartenImageListe = new ArrayList<ImageIcon>();

  /**
   * liefert eine Zufallszahl
   */
  private int createRandomNumber(int low, int high) {
    return (int) (Math.random() * (high - low + 1) + low);
  }

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

  public ImageIcon getKarteZufaellig() {
    int x = this.createRandomNumber(1, this.kartenImageListe.size());
    // index des arrays beginnt bei 0, daher 1 abziehen.
    return this.kartenImageListe.get(x-1);
  }

  /**
   * alle Karten (png) im Verzeichnis images laden
   */
  public void ladeKarten(int imageWidth, int imageHeigth) {

    File dir = new File("Karten");
    File[] kartenFiles = dir.listFiles();

    if (kartenFiles == null) {
      throw new RuntimeException("keine Karten gefunden");
    }

    for (File kartenFile : kartenFiles) {

      // nur Dateien mit der Endung .png laden
      if(!kartenFile.getName().endsWith(".png")) {
        continue;
      }

      Image image = new ImageIcon(kartenFile.getAbsolutePath()).getImage().getScaledInstance(imageWidth, imageHeigth, Image.SCALE_SMOOTH);
      this.kartenImageListe.add(new ImageIcon(image));
    }
  }

  /*Calendar cal = Calendar.getInstance();
    System.out.println("Datum:"+cal.get(Calendar.DAY_OF_MONTH)+"."+;
    (cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR));
    System.out.println("Uhrzeit:"+cal.get(Calendar.HOUR_OF_DAY)+":"+;
    cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+":"+;
    cal.get(Calendar.MILLISECOND));   */
} // end of class Casino
