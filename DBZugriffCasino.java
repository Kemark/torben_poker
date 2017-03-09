import java.sql.ResultSet;
import java.sql.*;

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
  public int erfasseKunde(Casino pCasino)
  {
    boolean rc = true;
    String sql;
    
    sql = "INSERT INTO Casino_Poker (Name, Passwort, letzterEinsatz, Kontostand) VALUES ('" + pCasino.getName() + "','" + pCasino.getPasswort() + "'," + pCasino.getEinsatz() + "," + pCasino.getKontostand() + ");";
    
    // Bei der Bearbeitung moegliche Fehler pruefen und entsprechende Fehlercodes zurueckgeben
    // 1. Datenbank oeffnen
    rc = meineDBVerbindung.oeffneDB();
    if (rc == false)
      return -101;
    
    // 2. Artikel erfassen
    rc = meineDBVerbindung.aendereDB(sql);
    if (rc == false)
      return -102;
    
    // 3.Datenbank schliessen
    rc = meineDBVerbindung.schliesseDB();
    if (rc == false)
      return -103;
  
    // alles ok!
    return 0;
  }
  
  
  // Methode zum Suchen eines Artikels  
  public int sucheKunde(Casino pCasino)
  {

    boolean rc = true;
    int fehlerCode=0;
    String sql;
    
    // SQL-Statement zusammenbauen
    sql = "SELECT * FROM Casino_Poker WHERE Name='"+pCasino.getName()+"';";
    // 1. Datenbank �ffnen
    rc = meineDBVerbindung.oeffneDB();
    if (rc==false) {
        return -204;
    } // end of if
    // 2. SQL-Statement ausfuehren, Datensatz lesen
    ResultSet meinSQLErgebnis = meineDBVerbindung.leseDB(sql);
    
    // versuche ersten Satz zu lesen (Da Nr eindeutig, gibt es immer maximal nur einen Datensatz)
      try
      {
        rc = meinSQLErgebnis.first();
        if (rc == false)
        {
          fehlerCode = -204; //Datensatz zu dieser Nummer nicht vorhanden
        }
        else
        {
          // Inhalt des Datensatzes in das Objekt uebernehmen
          pCasino.setName(meinSQLErgebnis.getString("Name"));
          pCasino.setPasswort(meinSQLErgebnis.getString("Passwort"));
          pCasino.setKontostand(meinSQLErgebnis.getInt("Kontostand"));
          pCasino.setEinsatz(meinSQLErgebnis.getInt("Einsatz"));
          
        }
      } 
      catch (Exception ex)
      {
        fehlerCode = -205;
      }
  
    // 3.Datenbank schliessen
    
    return fehlerCode;
  }
  
  
  // Methode zum Loeschen eines Artikels 
  public int loescheKunde(Casino pCasino)
  {

    boolean rc = true;
    String sql;
    
    // SQL-Statement zusammenbauen
     sql= "DELETE FROM Casino_Poker WHERE Name='"+pCasino.getName()+"';";
    
    // 1. Datenbank oeffnen
      rc = meineDBVerbindung.oeffneDB();
      if (rc==false) {
        return -204;
    } // end of if
    // 2. Artikel loeschen
       rc = meineDBVerbindung.aendereDB(sql);
    if (rc == false)
      return -102;
    // 3.Datenbank schliessen
      rc = meineDBVerbindung.schliesseDB();
    if (rc == false)
      return -103;
    // alles ok!
    return 0;
  }
  
  // Methode zum aendern eines Artikels, sofern ein Artikel zu dieser Nummer existiert
  public int aendereKunde(Casino pCasino)
  {

    boolean rc = true;
    String sql;
    
    // SQL-Statement zusammenbauen
    
    // 1. Datenbank �ffnen
    
    // 2. Artikel aendern
    
    // 3.Datenbank schliessen
    
    // alles ok!
    return 0;
  }
}