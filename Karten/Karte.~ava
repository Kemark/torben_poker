package BJ_pack;


// Klasse der Spielkarten
public class Karte
{
  private Spielkartenfarbe farbe; // Spielkartenfarbe (Herz, Pik, Karo, Kreuz)
  private int wert;         // Spielkartenwert (As = 11)
  private String name;      // Karte (As)
  private int nummer;       // lfd. Nummer im Deck
  private String resource;    // Name der Datei mit dem Bild der Karte
  private String komplettName;  // Kompletter Name  (Herz As)

  public String getKomplettName()
  {
    return komplettName;
  }
  public void setKomplettName(String pKomplettName)
  {
    komplettName = pKomplettName;
  }
  public String getResource()
  {
    return resource;
  }
  public void setResource(String pResource)
  {
    resource = pResource;
  }
  public int getNummer()
  {
    return nummer;
  }
  public void setNummer(int pNummer)
  {
    nummer = pNummer;
  }
  public Spielkartenfarbe getFarbe()
  {
    return farbe;
  }
  public void setFarbe(Spielkartenfarbe pFarbe)
  {
    farbe = pFarbe;
  }
  public int getWert()
  {
    return wert;
  }
  public void setWert(int pWert)
  {
    wert = pWert;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String pName)
  {
    name = pName;
  }
  
  // Konstruktor
  public Karte(Spielkartenfarbe pFarbe, int pWert, String pName)
  {
    setFarbe(pFarbe);
    setName(pName);
    setNummer(pNummer);
    setResource(pFarbe.toString()+pName+".png");
    setKomplettName(pFarbe.toString()+" "+pName);
  }

}
