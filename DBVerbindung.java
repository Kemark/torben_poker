import java.sql.*;

public class DBVerbindung
{
  // Zwei Variablen werden in der Klasse benötigt
  // 1. Verbindungsobjekt
  private java.sql.Connection meineDBVerbindung;
  
  // 2. Objekt für die Ausführung von SQL-Statements
  private Statement meinSqlStatement = null;  

  // Methode zum Öffnen der Datenbankverbindung und initiieren des SQL-Statements
  public void oeffneDB()  
  {
    // Try-Catch muss für die Behandlung von Ausnahmefällen (z.B. Datenbank nicht vorhanden) benutzt werden
    try
    {
      // Verbindungsaufbau in drei Schritten, Reihenfolge ist wichtig
      // 1. Datenbanktreiber für Java laden
      Class.forName("com.mysql.jdbc.Driver").newInstance();
       
      // 2. Verbindung aufbauen
      meineDBVerbindung = DriverManager.getConnection("jdbc:mysql://172.17.128.8/inf12-03-04","inf12-03-04","10820");
      //meineDBVerbindung = DriverManager.getConnection("jdbc:mysql://localhost/poker","root","admin");
      
      // 3. Objekt für die Ausführung von SQL-Statements erzeugen
      meinSqlStatement = meineDBVerbindung.createStatement();
    }
    
    // im Falle eines Fehlers false zurückgeben
    catch (Exception ex)
    {
      throw new RuntimeException("Datenbankverbindung kann nicht hergestellt werden", ex);
    }    
  }
  
  // Methode zum Schließen der DB
  public void schliesseDB()
  {
    // Variable für den Rückgabewert
    try
    {
      meinSqlStatement.close();
      meineDBVerbindung.close();
    }
    // Im Fehlerfall Returncode auf false
    catch (SQLException err) 
    {
      throw new RuntimeException("Datenbankverbindung kann nicht geschlossen werden", err);
    }
  }

  // Methode zum Ändern eines Datensatzes in der DB
  public void aendereDB(String pSQL)
  { 
    // Variable für den Rückgabewert
    boolean rc = true;
    try 
    { // Ausführung des Updates
      meinSqlStatement.executeUpdate(pSQL); 
    }
    // Im Fehlerfall Returncode auf false
    catch (SQLException err) 
    {
      throw new RuntimeException("Datenbank kann nicht geändert werden", err);
    }
  }
  
  
  // Methode zum Lesen eines Datensatzes aus der DB
  // Methode gibt den Datensatz zurück, im Fehlerfall "null"
  public ResultSet leseDB(String pSQL)                 
  {
    try
    { // Ausführen der Angfrage an die Datenbank
      return meinSqlStatement.executeQuery(pSQL);
    }
    // Im Fehlerfall Ergebnis auf null setzen
    catch(SQLException err)
    {
      throw new RuntimeException("Datenbank kann nicht gelesen werden", err);
    }
  }
}
