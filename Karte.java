import javax.swing.ImageIcon;

// Klasse der Spielkarten
public class Karte
{
  private String farbe; // Spielkartenfarbe (Herz, Pik, Karo, Kreuz)
  private int wert;         // Spielkartenwert (As = 11)
  private String name;      // Karte (As)
  private int nummer;       // lfd. Nummer im Deck
  private String resource;    // Name der Datei mit dem Bild der Karte
  private String komplettName;  // Kompletter Name  (Herz As)
  private ImageIcon image;

  public String getKomplettName()
  {
    return komplettName;
  }
  public String getResource()
  {
    return resource;
  }
  public int getNummer()
  {
    return nummer;
  }
  public String getFarbe()
  {
    return farbe;
  }
  public int getWert()
  {
    return wert;
  }
  public String getName()
  {
    return name;
  }
  public ImageIcon getImage()
  {
    return image;
  }

  // Konstruktor
  public Karte(String pFarbe, int pWert, String pName, ImageIcon pImage)
  {
    farbe = pFarbe;
    name = pName;
    wert = pWert;
    image = pImage;
    // setResource(pFarbe.toString()+pName+".png");
    // setKomplettName(pFarbe.toString()+" "+pName);
  }
}
