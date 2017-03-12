import java.sql.*;
import java.util.Calendar;
import java.sql.ResultSet;

public class DBZugriffCasino
{
  
  private DBVerbindung meineDBVerbindung ;
  
  // Standardkonstruktor
  public DBZugriffCasino()
  { 
    // mit dem Erzeugen des Objekts wird die neue Datenbankverbindung angelegt
    meineDBVerbindung = new DBVerbindung();
  } 
  
  // Methode zum Erfassen eines Artikels
  public void erfasseKunde(Casino pCasino)
  {
    String sql;
    
    sql = "INSERT INTO Casino_Poker (Name, Passwort, Einsatz, Kontostand) VALUES ('" + pCasino.getName() + "','" + pCasino.getPasswort() + "'," + pCasino.getEinsatz() + "," + pCasino.getKontostand() + ");";
    
    // Bei der Bearbeitung moegliche Fehler pruefen und entsprechende Fehlercodes zurueckgeben
    // 1. Datenbank oeffnen
    meineDBVerbindung.oeffneDB();
    
    // 2. Artikel erfassen
    meineDBVerbindung.aendereDB(sql);
    
    // 3.Datenbank schliessen
    meineDBVerbindung.schliesseDB();
  }
  
  
  // Methode zum Suchen eines Artikels  
  public void sucheKunde(Casino pCasino)
  {
    String sql;
    
    // SQL-Statement zusammenbauen
    sql = "SELECT * FROM Casino_Poker WHERE Name='" + pCasino.getName() + "';";
 
    // 1. Datenbank oeffnen
    meineDBVerbindung.oeffneDB();
    ResultSet meinSQLErgebnis;

    try {
      // 2. SQL-Statement ausfuehren, Datensatz lesen
      meinSQLErgebnis = meineDBVerbindung.leseDB(sql);
      // versuche ersten Satz zu lesen (Da Name eindeutig, gibt es immer maximal nur einen Datensatz)
      if(meinSQLErgebnis.next() == false) {
        throw new RuntimeException("Benutzer ''" + pCasino.getName() + "'' existiert nicht");
      }
    }
    catch(SQLException ex) {
      throw new RuntimeException("Datenbankzugriff nicht möglich", ex);
    }

    try
    {
      // Inhalt des Datensatzes in das Objekt uebernehmen
      pCasino.setName(meinSQLErgebnis.getString("Name"));
      pCasino.setPasswort(meinSQLErgebnis.getString("Passwort"));
      pCasino.setKontostand(meinSQLErgebnis.getInt("Kontostand"));
      pCasino.setEinsatz(meinSQLErgebnis.getInt("Einsatz"));
    } 
    catch (Exception ex)
    {
      throw new RuntimeException("suche des Kunden nicht möglich", ex);
    }
  
    // 3.Datenbank schliessen
    meineDBVerbindung.schliesseDB();
  }
    
  // Methode zum aendern eines Artikels, sofern ein Artikel zu dieser Nummer existiert
  public void aendereKunde(Casino pCasino)
  {
    String sql;
    
    // SQL-Statement zusammenbauen
    sql = "UPDATE Casino_Poker SET Einsatz=" +pCasino.getEinsatz()+ ", Kontostand="+pCasino.getKontostand()+" WHERE Name='"+pCasino.getName()+"';";
    
    try {
      // 1. Datenbank �ffnen
      meineDBVerbindung.oeffneDB();
      
      // 2. Artikel aendern
      meineDBVerbindung.aendereDB(sql);
      
      // 3.Datenbank schliessen
      meineDBVerbindung.schliesseDB();    
    }
    catch(RuntimeException ex) {
      throw new RuntimeException("Einsatz und Kontostand kann nicht in die Datenbank geschrieben werden", ex);
    }
  }

  // Methode zum aendern eines Artikels, sofern ein Artikel zu dieser Nummer existiert
  public void erfasseSpielDaten(Casino pCasino, int kontostand_alt)
  {
    String sql;
    
    Calendar cal = Calendar.getInstance();

    String aktuellerZeitpunkt = cal.get(Calendar.YEAR)
        + "/" + (cal.get(Calendar.MONTH)+1) 
        + "/" + cal.get(Calendar.DAY_OF_MONTH) 
        + " " + cal.get(Calendar.HOUR_OF_DAY)
        + ":" + cal.get(Calendar.MINUTE)
        + ":" + cal.get(Calendar.SECOND)
        + "." + cal.get(Calendar.MILLISECOND);
    
    // SQL-Statement zusammenbauen
    sql = "INSERT INTO Casino_Spiel (Name, Nummer, Kontostand_alt, Kontostand, AktuellerZeitpunkt) VALUES ('" + pCasino.getName() + "', 1," + kontostand_alt + "," + pCasino.getKontostand() + ", '" + aktuellerZeitpunkt + "');";
    
    try {
      // 1. Datenbank �ffnen
      meineDBVerbindung.oeffneDB();
      
      // 2. Artikel aendern
      meineDBVerbindung.aendereDB(sql);
      
      // 3.Datenbank schliessen
      meineDBVerbindung.schliesseDB();    
    }
    catch(Exception ex) {
      throw new RuntimeException("Einsatz und Kontostand kann nicht in die Datenbank geschrieben werden", ex);
    }
  }
}