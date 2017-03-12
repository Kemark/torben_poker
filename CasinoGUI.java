import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Calendar;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 13.02.2017
  * @author 
*/
public class CasinoGUI extends Frame {

  // Anfang Attribute
  private JLabel lblName = new JLabel();
  private JLabel lblPasswort = new JLabel();
  private JLabel lblKontostand = new JLabel();
  private JLabel lblEinsatz = new JLabel();
  private JTextField tfEinsatz = new JTextField();
  private JButton btnChange1 = new JButton();
  private JButton btnChange2 = new JButton();
  private JButton btnChange3 = new JButton();
  private JButton btnChange4 = new JButton();
  private JButton btnChange5 = new JButton();
  private JButton btnRegistrieren = new JButton();
  private JButton btnAnmelden = new JButton();
  private JButton btnAbmelden = new JButton();
  private JLabel lblCard1 = new JLabel();
  private JLabel lblCardbackground2 = new JLabel();
  private JLabel lblCardBackground3 = new JLabel();
  private JLabel lblCardBackground4 = new JLabel();
  private JLabel lblCardBackground5 = new JLabel();
  private JLabel lblCardBackground1 = new JLabel();
  private JLabel lblStrich = new JLabel();
  private JLabel lblCard2 = new JLabel();
  private JLabel lblCard3 = new JLabel();
  private JLabel lblCard4 = new JLabel();
  private JLabel lblCard5 = new JLabel();
  private JTextField tfName = new JTextField();
  private JTextField tfPasswort = new JTextField();
  private JLabel jLabel1 = new JLabel();
  private ImageIcon jLabel1Icon = new ImageIcon("images/Bellagiofinish.png");
  private JButton btnStart = new JButton();
  private JTextField tfKontostand = new JTextField();
  // Ende Attribute

  private Casino casino = new Casino();

  // globale Einstellungen wie farben und Font
  private Color global_clrButtonForeground = Color.BLUE;
  private Font global_font = new Font("Avenir Next W01 Light", Font.PLAIN, 16);

  /**
   * siehe http://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
   */
  private KeyAdapter numberCheckKeyAdapter = new KeyAdapter() {

    public void keyTyped(KeyEvent e) 
    {
      switch(e.getKeyChar()) {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        // loeschen der eingegeben Zahlen ist auch erlaubt
        case KeyEvent.VK_BACK_SPACE:
        case KeyEvent.VK_DELETE:
          // es werden nur die zuvor definierten Zeichen verarbeitet
          break;
        default:
          // aller anderen Zeichen werden ignoriert
          e.consume();
      }
    }
  };

  /**
   * 
   */
  public CasinoGUI() {

    // Frame-Initialisierung
    super();
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        dispose();
      }
    });

    int frameWidth = 1200;
    int frameHeight = 800;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Poker - Texas Hold'em");
    setResizable(false);
    Panel cp = new Panel(null);
    add(cp);

    // Anfang Komponenten
    cp.setBackground(new Color(0x228B22));
    lblName.setBounds(200, 40, 52, 22);
    lblName.setText("Name:");
    lblName.setForeground(Color.WHITE);
    lblName.setFont(global_font);
    cp.add(lblName);
    lblPasswort.setBounds(460, 40, 120, 22);
    lblPasswort.setText("Passwort:");
    lblPasswort.setForeground(Color.WHITE);
    lblPasswort.setFont(global_font);
    cp.add(lblPasswort);
    lblKontostand.setBounds(820, 40, 120, 22);
    lblKontostand.setText("Kontostand:");
    lblKontostand.setForeground(Color.WHITE);
    lblKontostand.setFont(global_font);
    cp.add(lblKontostand);
    lblEinsatz.setBounds(955, 600, 90, 35);
    lblEinsatz.setText("Einsatz:");
    lblEinsatz.setOpaque(false);
    lblEinsatz.setForeground(Color.WHITE);
    lblEinsatz.setFont(global_font);
    cp.add(lblEinsatz);
    tfEinsatz.setBounds(950, 630, 80, 25);
    tfEinsatz.setText("");
    tfEinsatz.setBackground(new Color(0x228B22));
    tfEinsatz.setForeground(Color.WHITE);
    tfEinsatz.setFont(global_font);
    tfEinsatz.addKeyListener(this.numberCheckKeyAdapter);
    cp.add(tfEinsatz);

    btnChange1.setBounds(150, 445, 90, 35);
    btnChange1.setText("Change");
    btnChange1.setEnabled(false);
    btnChange1.setMargin(new Insets(2, 2, 2, 2));
    btnChange1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnChange1_ActionPerformed(evt);
      }
    });
    // btnChange1.setForeground(Color.YELLOW);
    btnChange1.setBackground(global_clrButtonForeground);

    btnChange1.setFont(global_font);
    cp.add(btnChange1);

    btnChange2.setBounds(350, 445, 90, 35);
    btnChange2.setText("Change");
    btnChange2.setEnabled(false);
    btnChange2.setMargin(new Insets(2, 2, 2, 2));
    btnChange2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnChange2_ActionPerformed(evt);
      }
    });
    btnChange2.setBackground(global_clrButtonForeground);
    btnChange2.setFont(global_font);
    cp.add(btnChange2);

    btnChange3.setBounds(550, 445, 90, 35);
    btnChange3.setText("Change");
    btnChange3.setEnabled(false);
    btnChange3.setMargin(new Insets(2, 2, 2, 2));
    btnChange3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnChange3_ActionPerformed(evt);
      }
    });
    btnChange3.setBackground(global_clrButtonForeground);
    btnChange3.setFont(global_font);
    cp.add(btnChange3);

    btnChange4.setBounds(750, 445, 90, 35);
    btnChange4.setText("Change");
    btnChange4.setEnabled(false);
    btnChange4.setMargin(new Insets(2, 2, 2, 2));
    btnChange4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnChange4_ActionPerformed(evt);
      }
    });
    btnChange4.setBackground(global_clrButtonForeground);
    btnChange4.setFont(global_font);
    cp.add(btnChange4);

    btnChange5.setBounds(950, 445, 90, 35);
    btnChange5.setText("Change");
    btnChange5.setEnabled(false);
    btnChange5.setMargin(new Insets(2, 2, 2, 2));
    btnChange5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnChange5_ActionPerformed(evt);
      }
    });
    btnChange5.setBackground(global_clrButtonForeground);
    btnChange5.setFont(global_font);
    cp.add(btnChange5);

    btnRegistrieren.setBounds(100, 600, 120, 35);
    btnRegistrieren.setText("Registrieren");
    btnRegistrieren.setMargin(new Insets(2, 2, 2, 2));
    btnRegistrieren.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnRegistrieren_ActionPerformed(evt);
      }
    });
    btnRegistrieren.setBackground(global_clrButtonForeground);
    btnRegistrieren.setFont(global_font);
    cp.add(btnRegistrieren);
    btnAnmelden.setBounds(100, 630, 120, 35);
    btnAnmelden.setText("Anmelden");
    btnAnmelden.setMargin(new Insets(2, 2, 2, 2));
    btnAnmelden.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnAnmelden_ActionPerformed(evt);
      }
    });
    btnAnmelden.setBackground(global_clrButtonForeground);
    btnAnmelden.setFont(global_font);
    cp.add(btnAnmelden);
    btnAbmelden.setBounds(100, 660, 120, 35);
    btnAbmelden.setText("Abmelden");
    btnAbmelden.setMargin(new Insets(2, 2, 2, 2));
    btnAbmelden.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnAbmelden_ActionPerformed(evt);
      }
    });
    btnAbmelden.setBackground(global_clrButtonForeground);
    btnAbmelden.setFont(global_font);
    cp.add(btnAbmelden);

    lblCard1.setBounds(105, 195, 170, 220);
    lblCard1.setText("");
    lblCard1.setForeground(new Color(0xEEEEEE));
    lblCard1.setOpaque(true);
    lblCard1.setBackground(new Color(0x228B22));
    cp.add(lblCard1);

    lblCard2.setBounds(305, 195, 170, 220);
    lblCard2.setText("");
    lblCard2.setBackground(new Color(0x228B22));
    lblCard2.setOpaque(true);
    lblCard2.setForeground(new Color(0xEEEEEE));
    cp.add(lblCard2);

    lblCard3.setBounds(505, 195, 170, 220);
    lblCard3.setText("");
    lblCard3.setBackground(new Color(0x228B22));
    lblCard3.setOpaque(true);
    lblCard3.setForeground(new Color(0xEEEEEE));
    cp.add(lblCard3);

    lblCard4.setBounds(705, 195, 170, 220);
    lblCard4.setText("");
    lblCard4.setBackground(new Color(0x228B22));
    lblCard4.setOpaque(true);
    lblCard4.setForeground(new Color(0xEEEEEE));
    cp.add(lblCard4);

    lblCard5.setBounds(905, 195, 170, 220);
    lblCard5.setText("");
    lblCard5.setBackground(new Color(0x228B22));
    lblCard5.setOpaque(true);
    lblCard5.setForeground(new Color(0xEEEEEE));
    cp.add(lblCard5);

    lblCardBackground1.setBounds(100, 190, 180, 230);
    lblCardBackground1.setText("");
    lblCardBackground1.setBackground(Color.WHITE);
    lblCardBackground1.setOpaque(true);
    lblCardBackground1.setForeground(new Color(0xEEEEEE));
    cp.add(lblCardBackground1);

    lblCardbackground2.setBounds(300, 190, 180, 230);
    lblCardbackground2.setText("");
    lblCardbackground2.setBackground(Color.WHITE);
    lblCardbackground2.setOpaque(true);
    lblCardbackground2.setForeground(new Color(0xEEEEEE));
    cp.add(lblCardbackground2);

    lblCardBackground3.setBounds(500, 190, 180, 230);
    lblCardBackground3.setText("");
    lblCardBackground3.setBackground(Color.WHITE);
    lblCardBackground3.setOpaque(true);
    lblCardBackground3.setForeground(new Color(0xEEEEEE));
    cp.add(lblCardBackground3);

    lblCardBackground4.setBounds(700, 190, 180, 230);
    lblCardBackground4.setText("");
    lblCardBackground4.setBackground(Color.WHITE);
    lblCardBackground4.setOpaque(true);
    lblCardBackground4.setForeground(new Color(0xEEEEEE));
    cp.add(lblCardBackground4);

    lblCardBackground5.setBounds(900, 190, 180, 230);
    lblCardBackground5.setText("");
    lblCardBackground5.setBackground(Color.WHITE);
    lblCardBackground5.setOpaque(true);
    lblCardBackground5.setForeground(new Color(0xEEEEEE));
    cp.add(lblCardBackground5);

    lblStrich.setBounds(100, 100, 980, 1);
    lblStrich.setText("");
    lblStrich.setBackground(Color.WHITE);
    lblStrich.setOpaque(true);
    cp.add(lblStrich);

    tfName.setBounds(260, 40, 180, 20);
    tfName.setBackground(new Color(0x228B22));
    tfName.setForeground(Color.WHITE);
    tfName.setFont(global_font);
    cp.add(tfName);

    tfPasswort.setBounds(550, 40, 120, 20);
    tfPasswort.setBackground(new Color(0x228B22));
    tfPasswort.setForeground(Color.WHITE);
    tfPasswort.setFont(global_font);
    cp.add(tfPasswort);

    jLabel1.setBounds(390, 530, 400, 200);
    jLabel1.setText("");
    jLabel1.setIcon(jLabel1Icon);
    jLabel1.setBackground(new Color(0x228B22));
    jLabel1.setOpaque(true);
    jLabel1.setFont(global_font);
    cp.add(jLabel1);

    btnStart.setBounds(920, 530, 140, 50);
    btnStart.setText("START");
    btnStart.setEnabled(false);
    btnStart.setMargin(new Insets(2, 2, 2, 2));
    btnStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnStart_ActionPerformed(evt);
      }
    });
    btnStart.setBackground(new Color(0xFE2E2E));
    btnStart.setFont(global_font);
    cp.add(btnStart);

    tfKontostand.setBounds(930, 40, 100, 20);
    tfKontostand.setBackground(new Color(0x228B22));
    tfKontostand.setForeground(Color.WHITE);
    tfKontostand.setFont(global_font);
    tfKontostand.addKeyListener(this.numberCheckKeyAdapter);
    cp.add(tfKontostand);
    // Ende Komponenten

    // am Ende aller karten laden
    casino.ladeKarten(170, 220);

    // alle Karten initial laden. Es wird immer die Kartenrueckseite gesetzt.
    lblCard1.setIcon(casino.getKarteRueckseite());
    lblCard2.setIcon(casino.getKarteRueckseite());
    lblCard3.setIcon(casino.getKarteRueckseite());
    lblCard4.setIcon(casino.getKarteRueckseite());
    lblCard5.setIcon(casino.getKarteRueckseite());

    setVisible(true);
  } // end of public CasinoGUI

  // Anfang Methoden

  public static void main(String[] args) {
    new CasinoGUI();
  } // end of main

  public void btnChange1_ActionPerformed(ActionEvent evt) {
    lblCard1.setIcon(casino.getObersteKarte(1));
    btnChange1.setEnabled(false);
  } // end of btnChange1_ActionPerformed

  public void btnChange2_ActionPerformed(ActionEvent evt) {
    lblCard2.setIcon(casino.getObersteKarte(2));
    btnChange2.setEnabled(false);
  } // end of btnChange2_ActionPerformed

  public void btnChange3_ActionPerformed(ActionEvent evt) {
    lblCard3.setIcon(casino.getObersteKarte(3));
    btnChange3.setEnabled(false);
  } // end of btnChange3_ActionPerformed

  public void btnChange4_ActionPerformed(ActionEvent evt) {
    lblCard4.setIcon(casino.getObersteKarte(4));
    btnChange4.setEnabled(false);
  } // end of btnChange4_ActionPerformed

  public void btnChange5_ActionPerformed(ActionEvent evt) {
    lblCard5.setIcon(casino.getObersteKarte(5));
    btnChange5.setEnabled(false);
  } // end of btnChange5_ActionPerformed

  /**
   * Benutzer fuer das Spiel registrieren
   */
  public void btnRegistrieren_ActionPerformed(ActionEvent evt) {

    // pruefe, ob Kontostand gesetzt ist
    if (tfKontostand.getText().equals("")) {
      JOptionPane.showMessageDialog(this, "Kontostand muss gefüllt sein", "Fehler", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // pruefe, ob Name gesetzt ist
    if (tfName.getText().equals("")) {
      JOptionPane.showMessageDialog(this, "Name muss definiert sein", "Fehler", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // pruefe, ob Name gesetzt ist
    if (tfPasswort.getText().equals("")) {
      JOptionPane.showMessageDialog(this, "Passwort muss definiert sein", "Fehler", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try {
      casino.registrieren(tfName.getText(), tfPasswort.getText(), tfKontostand.getText());
    }
    catch(RuntimeException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  } // end of btnRegistrieren_ActionPerformed

  /**
   * Anmeldung des Benutzer per Abfrage der Datenbank
   */
  public void btnAnmelden_ActionPerformed(ActionEvent evt) {
    try 
    {
      casino.anmelden(tfName.getText(), tfPasswort.getText());
      tfKontostand.setText(String.valueOf(casino.getKontostand()));
      tfEinsatz.setText(String.valueOf(casino.getEinsatz()));

      // Button freischalten, bzw. deaktivieren
      btnStart.setEnabled(true);
      btnRegistrieren.setEnabled(false);
      btnAnmelden.setEnabled(false);

      // Passwort Feld wird unsichtbar
      tfPasswort.setVisible(false);
      lblPasswort.setVisible(false);
    } 
    catch(RuntimeException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  } // end of btnAnmelden_ActionPerformed

  /**
   * Beendet das Spiel
   */
  public void btnAbmelden_ActionPerformed(ActionEvent evt) {
    try {
      casino.abmelden(tfName.getText(), tfKontostand.getText(), tfEinsatz.getText());
    } 
    catch(RuntimeException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
    }
    System.exit(0);
  } // end of btnAbmelden_ActionPerformed

  /**
   * Starten der Anwendung
   */
  public void btnStart_ActionPerformed(ActionEvent evt) {

    try {
      if (btnStart.getText().equals("START")) {

        // karten stapel erzeugen (mischen der karten)
        casino.start(this.tfKontostand.getText(), this.tfEinsatz.getText());

        // Einsatz und Kontostand deaktivieren
        tfEinsatz.setEnabled(false);
        tfKontostand.setEnabled(false);
        
        // erst mit Start werden alle buttons aktiv
        btnChange1.setEnabled(true);
        btnChange2.setEnabled(true);
        btnChange3.setEnabled(true);
        btnChange4.setEnabled(true);
        btnChange5.setEnabled(true);

        // oberste karte ziehen
        lblCard1.setIcon(casino.getObersteKarte(1));
        lblCard2.setIcon(casino.getObersteKarte(2));
        lblCard3.setIcon(casino.getObersteKarte(3));
        lblCard4.setIcon(casino.getObersteKarte(4));
        lblCard5.setIcon(casino.getObersteKarte(5));

        // button auf beenden setzen
        btnStart.setText("BEENDEN");
      }
      // Beenden Fall
      else {

          // bei Beenden werden alle buttons wieder deaktiviert
          casino.gewinnErmitteln();        
          tfKontostand.setText(Integer.toString(casino.getKontostand()));

          // Buttons wieder deaktivieren
          btnChange1.setEnabled(false);
          btnChange2.setEnabled(false);
          btnChange3.setEnabled(false);
          btnChange4.setEnabled(false);
          btnChange5.setEnabled(false);

          // Einsatz und Kontostand deaktivieren
          tfEinsatz.setEnabled(true);
          tfKontostand.setEnabled(true);

          btnStart.setText("START");
      }
    } catch(Exception ex) {

      // Kontostand kann zu gering sein
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  } // end of btnStart_AStart

  // Ende Methoden
} // end of class CasinoGUI
