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
  private JTextField nfEinsatz = new JTextField();
  private JButton btnChange1 = new JButton();
  private JButton btnChange2 = new JButton();
  private JButton btnChange3 = new JButton();
  private JButton btnChange4 = new JButton();
  private JButton btnChange5 = new JButton();
  private JButton btnChangeAll = new JButton();
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

  private Casino poker = new Casino(); 
  private DBZugriffCasino meinDBZugriff = new DBZugriffCasino();

  // globale Einstellungen wie farben und Font
  private Color global_clrButtonForeground = Color.BLUE;

  /**
   * 
   */
  public CasinoGUI() {

    // Frame-Initialisierung
    super();
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) { dispose(); }
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
    lblName.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 16));
    cp.add(lblName);
    lblPasswort.setBounds(475, 40, 70, 22);
    lblPasswort.setText("Passwort:");
    lblPasswort.setForeground(Color.WHITE);
    lblPasswort.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 16));
    cp.add(lblPasswort);
    lblKontostand.setBounds(830, 40, 87, 22);
    lblKontostand.setText("Kontostand:");
    lblKontostand.setForeground(Color.WHITE);
    lblKontostand.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 16));
    cp.add(lblKontostand);
    lblEinsatz.setBounds(955, 600, 90, 35);
    lblEinsatz.setText("Einsatz:");
    lblEinsatz.setOpaque(false);
    lblEinsatz.setForeground(Color.WHITE);
    lblEinsatz.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 18));
    cp.add(lblEinsatz);
    nfEinsatz.setBounds(950, 630, 80, 25);
    nfEinsatz.setText("");
    nfEinsatz.setBackground(new Color(0x228B22));
    nfEinsatz.setForeground(Color.WHITE);
    nfEinsatz.setFont(new Font("Garamond", Font.BOLD, 14));
    cp.add(nfEinsatz);

    btnChange1.setBounds(150, 445, 90, 35);
    btnChange1.setText("Change");
    btnChange1.setMargin(new Insets(2, 2, 2, 2));
    btnChange1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnChange1_ActionPerformed(evt);
      }
    });
    // btnChange1.setForeground(Color.YELLOW);
    btnChange1.setBackground(global_clrButtonForeground );

    btnChange1.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 14));
    cp.add(btnChange1);

    btnChange2.setBounds(350, 445, 90, 35);
    btnChange2.setText("Change");
    btnChange2.setMargin(new Insets(2, 2, 2, 2));
    btnChange2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnChange2_ActionPerformed(evt);
      }
    });
    btnChange2.setBackground(global_clrButtonForeground );
    btnChange2.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 14));
    cp.add(btnChange2);

    btnChange3.setBounds(550, 445, 90, 35);
    btnChange3.setText("Change");
    btnChange3.setMargin(new Insets(2, 2, 2, 2));
    btnChange3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) { 
        btnChange3_ActionPerformed(evt);
      }
    });
    btnChange3.setBackground(global_clrButtonForeground);
    btnChange3.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 14));
    cp.add(btnChange3);

    btnChange4.setBounds(750, 445, 90, 35);
    btnChange4.setText("Change");
    btnChange4.setMargin(new Insets(2, 2, 2, 2));
    btnChange4.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnChange4_ActionPerformed(evt);
      }
    });
    btnChange4.setBackground(global_clrButtonForeground);
    btnChange4.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 14));
    cp.add(btnChange4);

    btnChange5.setBounds(950, 445, 90, 35);
    btnChange5.setText("Change");
    btnChange5.setMargin(new Insets(2, 2, 2, 2));
    btnChange5.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnChange5_ActionPerformed(evt);
      }
    });
    btnChange5.setBackground(global_clrButtonForeground);
    btnChange5.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 14));
    cp.add(btnChange5);

    btnChangeAll.setBounds(545, 488, 100, 35);
    btnChangeAll.setText("Change All");
    btnChangeAll.setMargin(new Insets(2, 2, 2, 2));
    btnChangeAll.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnChangeAll_ActionPerformed(evt);
      }
    });
    btnChangeAll.setBackground(global_clrButtonForeground);
    btnChangeAll.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 14));
    cp.add(btnChangeAll);

    btnRegistrieren.setBounds(100, 600, 100, 25);
    btnRegistrieren.setText("Registrieren");
    btnRegistrieren.setMargin(new Insets(2, 2, 2, 2));
    btnRegistrieren.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnRegistrieren_ActionPerformed(evt);
      }
    });
    btnRegistrieren.setBackground(global_clrButtonForeground);
    btnRegistrieren.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 16));
    cp.add(btnRegistrieren);
    btnAnmelden.setBounds(100, 630, 100, 25);
    btnAnmelden.setText("Anmelden");
    btnAnmelden.setMargin(new Insets(2, 2, 2, 2));
    btnAnmelden.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnAnmelden_ActionPerformed(evt);
      }
    });
    btnAnmelden.setBackground(global_clrButtonForeground);
    btnAnmelden.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 16));
    cp.add(btnAnmelden);
    btnAbmelden.setBounds(100, 660, 100, 25);
    btnAbmelden.setText("Abmelden");
    btnAbmelden.setMargin(new Insets(2, 2, 2, 2));
    btnAbmelden.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnAbmelden_ActionPerformed(evt);
      }
    });
    btnAbmelden.setBackground(global_clrButtonForeground);
    btnAbmelden.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 16));
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
   
    tfName.setBounds(260, 40, 100, 20);
    tfName.setBackground(new Color(0x228B22));
    tfName.setForeground(Color.WHITE);
    tfName.setFont(new Font("Garamond", Font.BOLD, 14));
    cp.add(tfName);

    tfPasswort.setBounds(550, 40, 100, 20);
    tfPasswort.setBackground(new Color(0x228B22));
    tfPasswort.setForeground(Color.WHITE);
    tfPasswort.setFont(new Font("Garamond", Font.BOLD, 14));
    cp.add(tfPasswort);

    jLabel1.setBounds(390, 530, 400, 200);
    jLabel1.setText("");
    jLabel1.setIcon(jLabel1Icon);
    jLabel1.setBackground(new Color(0x228B22));
    jLabel1.setOpaque(true);
    jLabel1.setFont(new Font("Garamond", Font.BOLD, 12));
    cp.add(jLabel1);

    btnStart.setBounds(920, 530, 140, 50);
    btnStart.setText("START");
    btnStart.setMargin(new Insets(2, 2, 2, 2));
    btnStart.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnStart_ActionPerformed(evt);
      }
    });
    btnStart.setBackground(new Color(0xFE2E2E));
    btnStart.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 22));
    cp.add(btnStart);

    tfKontostand.setBounds(930, 40, 100, 20);
    tfKontostand.setBackground(new Color(0x228B22));
    tfKontostand.setForeground(Color.WHITE);
    tfKontostand.setFont(new Font("Garamond", Font.BOLD, 14));
    cp.add(tfKontostand);
    // Ende Komponenten
    
    // am Ende aller karten laden
    poker.ladeKarten(170, 220);

    // alle Karten initial laden. Entspricht der gleichen Aktion wie druecken des "change all" buttons.
    this.changeAll();

    setVisible(true);
  } // end of public CasinoGUI
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new CasinoGUI();
  } // end of main
  
  public void btnChange1_ActionPerformed(ActionEvent evt) {
    lblCard1.setIcon(poker.getKarteZufaellig());
  } // end of btnChange1_ActionPerformed

  public void btnChange2_ActionPerformed(ActionEvent evt) {
    lblCard2.setIcon(poker.getKarteZufaellig());
  } // end of btnChange2_ActionPerformed

  public void btnChange3_ActionPerformed(ActionEvent evt) {
    lblCard3.setIcon(poker.getKarteZufaellig());
  } // end of btnChange3_ActionPerformed

  public void btnChange4_ActionPerformed(ActionEvent evt) {
    lblCard4.setIcon(poker.getKarteZufaellig());
  } // end of btnChange4_ActionPerformed

  public void btnChange5_ActionPerformed(ActionEvent evt) {
    lblCard5.setIcon(poker.getKarteZufaellig());
  } // end of btnChangeAll_ActionPerformed

  private void changeAll() {
    // geladene Karten zuweisen
    lblCard1.setIcon(poker.getKarteZufaellig());
    lblCard2.setIcon(poker.getKarteZufaellig());
    lblCard3.setIcon(poker.getKarteZufaellig());
    lblCard4.setIcon(poker.getKarteZufaellig());
    lblCard5.setIcon(poker.getKarteZufaellig());
  }

  public void btnChangeAll_ActionPerformed(ActionEvent evt) {
    this.changeAll();
  } // end of btnChangeAll_ActionPerformed

  public void btnRegistrieren_ActionPerformed(ActionEvent evt) {
    erfassen();
  } // end of btnRegistrieren_ActionPerformed

  public void btnAnmelden_ActionPerformed(ActionEvent evt) {
    suchen();
  } // end of btnAnmelden_ActionPerformed

  public void btnAbmelden_ActionPerformed(ActionEvent evt) {
    aendern (); 
    System.exit(0);
  } // end of btnAbmelden_ActionPerformed

   public void btnStart_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
  } // end of btnStart_ActionPerformed
  
 /* MyPanel = new JPanel()
   {
     public void paintComponent(Graphics g) 
   {
        // Das Bild von der resource in eine Image Variable laden
        Image img = new ImageIcon(getClass().getResource("MiyPic.jpg")).getImage();
        g.drawImage(img, 0, 0, null);
      } 
    }; */
  
 /* private void MergeCards(int AnzahlMischvorgange)
  {
    // Mischen geschieht durch das Austauschen einer Karte an 
    // einer zufälligen Position im Deck mit der i.-ten Karte
    int TauschPosition=0;
    
    // Anzahl Mischvorgänge
    for (int j=0; j<AnzahlMischvorgange; j++)
    {
      // Austausch so oft wie Karten im Deck
      for (int i=0; i<Deck.size(); i++)
      {
        // zufällige Karte mit i.ten Karte austauschen
        TauschPosition= myRandom(0, Deck.size());
        Deck.add(TauschPosition, Deck.remove(i));
      }
    }
  }
  */  
  public void suchen () {
    poker.setName(tfName.getText() );
    poker.setPasswort(tfPasswort.getText());
    meinDBZugriff.sucheKunde(poker);
    tfKontostand.setText(String.valueOf(poker.getKontostand()));
    nfEinsatz.setText(Integer.toString(poker.getEinsatz()));
  }
 
  public void erfassen() {
    poker.setName(tfName.getText());
    poker.setPasswort(tfPasswort.getText());
    poker.setKontostand(Integer.parseInt(tfKontostand.getText()));
    poker.setEinsatz(Integer.parseInt(nfEinsatz.getText())); 
    meinDBZugriff.erfasseKunde(poker);
  }
    
  public void aendern() {
    int rc=0;
    poker.setName(tfName.getText());
    meinDBZugriff.sucheKunde(poker);
    poker.setKontostand(Integer.parseInt(tfKontostand.getText()));
    poker.setEinsatz(Integer.parseInt(nfEinsatz.getText()));
    meinDBZugriff.aendereKunde(poker);
    
    if (rc<0) {
      JOptionPane.showMessageDialog(null,"Zugriff auf DB fehlgeschlagen","Fehler", JOptionPane.INFORMATION_MESSAGE);
    } // end of if
  } 
 

  // Ende Methoden
} // end of class CasinoGUI
