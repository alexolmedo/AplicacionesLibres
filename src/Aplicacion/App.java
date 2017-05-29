package Aplicacion;

import Interfaces.*;
import javax.swing.JFrame;

public class App {
    public static void main(String args[]) {

        SplashScreen inicio = new SplashScreen();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        inicio.setVisible(false);
        inicio.dispose();
        
        new Login().setVisible(true);
        
        //System.setProperty("user.dir", "/home/andreu/NetBeansProjects/AplicacionesLibres/src");
        //new VentanaPrincipal("1723123459",2016).setVisible(true);
        //new VentanaPrincipal("1718269671",2016).setVisible(true);
        //new RegistroLimitesAnio().setVisible(true);
    }    
}
