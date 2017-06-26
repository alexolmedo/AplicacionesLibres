package Aplicacion;

import Interfaces.*;
import conexionBDD.InitDatabase;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class App {
    
    //------------------------------
    //Comentario de prueba de cambios ----
    //-------------
    
    public static void main(String args[]) {
        File base = new File("facturacion.db");
        if(!base.exists()){
            InitDatabase crearBase = new InitDatabase();
        }
        try{
  
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      }
      catch (Exception e)
       {
        e.printStackTrace();
       }
        
        splash s = new splash();
        s.setVisible(true);
        s.iniciar();
        //new Login().setVisible(true);
        
    }    
}
