    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
//GEN-END:initComponents

package xor;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.NumberFormat;
import javax.swing.*;

/**
 *
 * @author ahmet
 */
public class a extends javax.swing.JFrame implements ActionListener,Runnable {

  JButton egit;
  JButton calistir;
  JButton cik;
  JLabel durumlar;
  JTextField girdi;
  JTextField cikti;
  static  JTextField arakatman;
  static JTextField araeleman;
  static JTextField ogrenme;
  static JTextField momentum;
  protected Thread calisan = null;
  protected final static int girisSayisi = 2;
  protected final static int cikisSayisi = 1;
  protected JTextField data[][] = new JTextField[4][4];
  protected Network network;

    public a() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    
    private void initComponents() {
      
        setTitle("XOR Problemi Çözümü");
   
    Container content = getContentPane();

    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    content.setLayout(gridbag);

    c.fill = GridBagConstraints.NONE;
    c.weightx = 1.0;

    
    c.gridwidth = GridBagConstraints.REMAINDER; 
    c.anchor = GridBagConstraints.NORTHWEST;
    content.add(
               new JLabel(
                         "Eğitim bilgilerini giriniz:"),c);
    
    JPanel grid1 = new JPanel();
    grid1.setLayout(new GridLayout(3,4));
    grid1.add(new JLabel("girdi sayısı"));
   
    grid1.add( girdi=new JTextField(""));
     girdi.setSize(20, 20);
    grid1.add(new JLabel("çıktı sayısı :"));
    grid1.add(cikti=new JTextField(""));
    
  
    grid1.add(new JLabel("ara eleman sayısı :"));
    grid1.add(araeleman=new JTextField(""));
    grid1.add(new JLabel("öğrenme katsayısı :") );
    grid1.add(ogrenme=new JTextField(""));
    grid1.add(new JLabel("momentum katsayısı :") );
    grid1.add(momentum=new JTextField(""));
    grid1.add(new JLabel("ara katman sayısı :") );
    grid1.add(arakatman=new JTextField(""));
   
    
    
    
    JPanel grid = new JPanel();
    grid.setLayout(new GridLayout(5,4));
    grid.add(new JLabel("GİRİŞ1"));
    grid.add(new JLabel("GİRİŞ2"));
    grid.add(new JLabel("Beklenen çıkış   "));
    grid.add(new JLabel("Ağın çıkışı"));
       
    for ( int i=0;i<4;i++ ) {
      int x = (i&1);
      int y = (i&2)>>1;
      grid.add(data[i][0] = new JTextField(""+y));
      grid.add(data[i][1] = new JTextField(""+x));
      grid.add(data[i][2] = new JTextField(""+(x^y)));
      grid.add(data[i][3] = new JTextField("??"));
      data[i][0].setEditable(false);
      data[i][1].setEditable(false);
      data[i][3].setEditable(false);
    }
    content.add(grid1,c);
    content.add(grid,c);
   
   
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(egit = new JButton("Eğit"));
    buttonPanel.add(calistir = new JButton("Çalıştır"));
    buttonPanel.add(cik = new JButton("Çık"));
    egit.addActionListener(this);
    calistir.addActionListener(this);
    cik.addActionListener(this);

   
    c.gridwidth = GridBagConstraints.REMAINDER; 
    c.anchor = GridBagConstraints.CENTER;
    content.add(buttonPanel,c);

    
    c.gridwidth = GridBagConstraints.REMAINDER; 
    c.anchor = GridBagConstraints.NORTHWEST;
    content.add(
               durumlar = new JLabel("Eğitime başlamak için eğit tuşuna tıklayınız..."),c);

    
    pack();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension d = toolkit.getScreenSize();
    setLocation(
               (int)(d.width-this.getSize().getWidth())/2,
               (int)(d.height-this.getSize().getHeight())/2 );
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);

    calistir.setEnabled(false);
    
    
    
  
    
    
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());

                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(a.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(a.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(a.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(a.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new a().setVisible(true);
               
            }
        });
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cik) {
            System.exit(0);
        } else if (e.getSource() == egit) {
            network = new Network(
                         girisSayisi,
                         Integer.parseInt(araeleman.getText()),
                         cikisSayisi,
                         Double.parseDouble(ogrenme.getText()),
                         Double.parseDouble(momentum.getText()));
                         System.out.println(Double.parseDouble(momentum.getText()));
                         System.out.println(Double.parseDouble(ogrenme.getText()));
                         System.out.println(Integer.parseInt(araeleman.getText()));
                         
            egit();
        } else if (e.getSource() == calistir) {
            hesapla();
        }
    }

    protected void hesapla() {
        double xorData[][] = getGrid();
        int    guncel      = 0;

        for (int i = 0; i < 4; i++) {
            NumberFormat nf  = NumberFormat.getInstance();
            double       d[] = network.cikislariHesapla(xorData[i]);

            data[i][3].setText(nf.format(d[0]));
        }
    }

    
    protected void egit() {
        if (calisan != null) {
            calisan = null;
        }

        calisan = new Thread(this);
        calisan.setPriority(Thread.MIN_PRIORITY);
        calisan.start();
    }

    
    public void run() {
        double xorData[][]  = getGrid();
        double xorIdeal[][] = getIdeal();
        int    guncel       = 0;
        int    max          = 10000;

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < xorData.length; j++) {
                network.cikislariHesapla(xorData[j]);
                network.hataHesapla(xorIdeal[j]);
                network.ogren();
            }

            guncel++;

            if (guncel == 100) {
                durumlar.setText("Tur sayısı:" + (max - i) + ",Hata:" + network.getError(xorData.length));
                guncel = 0;
            }
        }

        calistir.setEnabled(true);
    }

  
    double[][] getGrid() {
        double dizi[][] = new double[4][2];

        for (int i = 0; i < 4; i++) {
            dizi[i][0] = Float.parseFloat(data[i][0].getText());
            dizi[i][1] = Float.parseFloat(data[i][1].getText());
        }

        return dizi;
    }

    
    double[][] getIdeal() {
        double dizi[][] = new double[4][1];

        for (int i = 0; i < 4; i++) {
            dizi[i][0] = Float.parseFloat(data[i][2].getText());
        }

        return dizi;
    }
}



