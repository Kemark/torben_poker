/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 13.02.2017
  * @author 
  */
import java.io. * ; 
import java.awt. * ; 
import java.util. * ; 
import javax.swing. * ; 
import javax.imageio. * ; 
import java.awt.image. * ; 
import java.util.Calendar; 

public class Casino {

  private String Passwort; 
  private String Name; 
  private int Kontostand; 
  private int Einsatz; 

  // array enthaelt alle verfuegbaren karten
  private ArrayList <Karte> kartenListe; 

  // array enthaelt alle noch nicht vergebenen karten. Es muss sichergestellt sein, dass
  // eine karte nicht doppelt vergeben wird.
  private ArrayList <Karte> kartenStapel; 

  // array enthaelt die gesetzten karten
  private Karte[] aktuelleKarten;

  // feld enthaelt die karten rueckseite
  private ImageIcon kartenrueckseite; 

  // datenbank zugriff
  private DBZugriffCasino meinDBZugriff; 

  /**
   * Konstruktor
   */
  public Casino() {
    this.kartenListe = new ArrayList<Karte>();
    this.meinDBZugriff = new DBZugriffCasino();
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

  /**
   * neuen Benutzer registrieren
   */
  public void registrieren(String name, String passwort, String kontostand) {

    this.setName(name); 
    this.setPasswort(passwort); 
    this.setKontostand(Integer.parseInt(kontostand));

    try {
      meinDBZugriff.erfasseKunde(this); 
    }
    catch(Exception ex) {
      throw new RuntimeException("Benuter " + name + " existiert bereits!");
    }
  }

  /**
   * Anmeldung eines bestehenden Benutzers
   */
  public void anmelden(String name, String passwort) {

    this.setName(name); 
    meinDBZugriff.sucheKunde(this);

    if(!passwort.equals(this.getPasswort())) {
      throw new RuntimeException("Passwort ist falsch");
    }
  }

  /**
   * beim Abmelden wird der letzte Kontostand und Einsatz in die zurueckgeschrieben.
   */
  public void abmelden(String name, String kontostand, String einsatz) {

    this.setName(name); 
    this.setKontostand(Integer.parseInt(kontostand)); 
    this.setEinsatz(Integer.parseInt(einsatz)); 
    meinDBZugriff.aendereKunde(this);
  }

  /**
   * bereitet das Spiel vor. Legt Kartenstapel an....
   */
  public void start(String strKontostand, String strEinsatz) {

    if(strEinsatz.equals("")) {
      throw new RuntimeException("Einsatz daf nicht leer sein");
    }

    if(strKontostand.equals("")) {
      throw new RuntimeException("Kontostand darf nicht leer sein");
    }

    // vor dem Start prüfen ob 
    int einsatz = Integer.parseInt(strEinsatz);
    int kontostand = Integer.parseInt(strKontostand);

    if(einsatz == 0) {
      throw new RuntimeException("Es muss ein Einsatz gesetzt sein");
    }

    if(kontostand == 0) {
      throw new RuntimeException("Es muss ein Kontostand gesetzt sein");
    }

    if(einsatz > kontostand) {
      throw new RuntimeException("Kontostand ist zu gering");
    }

    // legt den kartenstapel an. 
    this.kartenStapel = new ArrayList<Karte>();
    // temporaere kartenstapel wird benoetigt, damit eine bereits gezogene karte aus dem Stapel
    // entfernt werden kann und nicht doppelt verwendet wird.
    ArrayList<Karte> tempKartenListe = (ArrayList<Karte>)this.kartenListe.clone();

    // karten stapel erzeugen. Entspricht dem mischen aller karten.
    while(tempKartenListe.size() > 0) {

      // Zufallszahl generieren (laut Beschreibung)
      int randomNumber = this.createRandomNumber(1, tempKartenListe.size());

      // index des arrays beginnt bei 0, daher 1 abziehen.
      Karte ermittelteKarte = tempKartenListe.get(randomNumber-1);

      // gezogen karte aus liste entfernen
      tempKartenListe.remove(randomNumber-1);

      // ermittelte Karte in Stapel einfuegen
      this.kartenStapel.add(ermittelteKarte);
    }

    // list der aktuelle gesetzten karten wird angelegt.
    this.aktuelleKarten = new Karte[5];
  }

  /**
   * liefert eine Zufallszahl
   */
  private int createRandomNumber(int low, int high) {

    // Berechnung der Zufallszahl laut Beschreibung
    return (int)(Math.random() * (high - low + 1) + low); 
  }

  /**
   * Liefert eine Karte zufaellig zurueck
   */
  public ImageIcon getObersteKarte(int index) {

    // oberste Karte ermitteln. Oberste karte hat immer den index 0.
    Karte ermittelteKarte = this.kartenStapel.get(0);

    // karte aus stapel entfernen, damit diese nicht zweimal vergeben werden kann.
    this.kartenStapel.remove(0);

    // ermittelte Karte in aktuelle Kartenliste eintragen
    aktuelleKarten[index-1] = ermittelteKarte; 

    // karten image zurueckliefern
    return ermittelteKarte.getImage(); 
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

      // Karten in Array übernehmen. Die Rueckseite wird jedoch in eine seperate Variable uebernommen.
      if(kartenFileName.equals("Rueck.png") ) {
        this.kartenrueckseite = angpepasstesImage;
      } 
      else 
      {
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

  /**
   * ermittelt den Karten gewinn
   */
  public void gewinnErmitteln() {

    int gewinn = 0;
    int einsatz = this.getEinsatz();

    // Klasse zur Gewinnermittlung
    Kombination gewinnErmittlung = new Kombination(aktuelleKarten);
    String kombination = gewinnErmittlung.getKombination();
    switch(kombination) {

      case "Royal Flush":
        gewinn = 9 * einsatz;
        break;

      case "Straigth Flush":
        gewinn = 8 * einsatz;
        break;

      case "Vierling":
        gewinn = 7 * einsatz;
        break;

      case "Full House":
        gewinn = 6 * einsatz;
        break;

      case "Flush":
        gewinn = 5 * einsatz;
        break;

      case "Strasse":
        gewinn = 4 * einsatz;
        break;

      case "Drilling":
        gewinn = 3 * einsatz;
        break;

      case "Zwei Paare":
        gewinn = 2 * einsatz;
        break;

      case "Paar":
        gewinn = 1 * einsatz;
        break;

      // kein Gewinn bedeutet Einsatz wird vom Kontastand abgezogen.
      default:
        gewinn = - einsatz;
    }

    if(!kombination.equals("")) {
      JOptionPane.showMessageDialog(null, "Glückwunsch!!!!\nSie haben ein " + kombination, "G E W I N N", JOptionPane.INFORMATION_MESSAGE);
    }

    int alterKontostand = this.getKontostand();
    this.setKontostand(this.getKontostand() + gewinn);

    // zuletzt noch den Spielstand für jedes Spiel in die Datenbank schreiben
    this.meinDBZugriff.erfasseSpielDaten(this, alterKontostand);
  }
} // end of class Casino
