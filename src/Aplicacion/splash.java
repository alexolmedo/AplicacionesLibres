package Aplicacion;

//import com.sun.awt.AWTUtilities;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import Interfaces.*;
import conexionBDD.InitDatabase;
import java.io.File;
import javax.swing.UIManager;

/**
 *
 * @author Gabriela
 */
public class splash extends javax.swing.JFrame {
    double i=70,j=1;
    cargar hilo;
    
    /**
     * Creates new form splash
     */
    public splash() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/ico_21-1.png")).getImage());
        File base = new File("facturacion.db");
        if(!base.exists()){
            InitDatabase crearBase = new InitDatabase();
        }
        
        //setIconImage(new ImageIcon(getClass().getResource("/Imagenes/doctor.png")).getImage());
      ((JPanel) getContentPane()).setOpaque(false);
       ImageIcon uno = new ImageIcon(this.getClass().getResource("/Imagenes/ImagenFondo.png"));
        JLabel fondo = new JLabel();
        fondo.setIcon(uno);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER); 
        fondo.setBounds(0, 0, uno.getIconWidth(), uno.getIconHeight());
        
        //this.setBounds(280, 200, fondo.getWidth(), fondo.getHeight());
        iniciar();
        //new Sis_Nutricion().setVisible(true);
    }
    
    public void iniciar(){
        setLocationRelativeTo(null);
        hilo=new cargar(getPbcargando());
        hilo.start();
        hilo=null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pbcargando = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pbcargando.setString("CARGANDO");
        pbcargando.setStringPainted(true);
        pbcargando.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pbcargandoStateChanged(evt);
            }
        });
        getContentPane().add(pbcargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 260, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ImagenFondo.png"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pbcargandoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pbcargandoStateChanged
        // TODO add your handling code here:
        if(pbcargando.getValue()==i){
            if(j!=101){
                //AWTUtilities.setWindowOpacity(this, Float.valueOf((100-j)/100+"f"));
            }
            i++;
            j+=3;
            
        }
        if(pbcargando.getValue()==100){
            new Login().setVisible(true);
            this.dispose();            
        }
            
        
    }//GEN-LAST:event_pbcargandoStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            //javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new splash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar pbcargando;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the pbcargando
     */
    public javax.swing.JProgressBar getPbcargando() {
        return pbcargando;
    }

    /**
     * @param pbcargando the pbcargando to set
     */
    public void setPbcargando(javax.swing.JProgressBar pbcargando) {
        this.pbcargando = pbcargando;
    }
}
