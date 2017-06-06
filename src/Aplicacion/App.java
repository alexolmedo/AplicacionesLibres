package Aplicacion;

import Interfaces.*;
import conexionBDD.InitDatabase;
import java.io.File;
import javax.swing.JFrame;

public class App {
    public static void main(String args[]) {
        File base = new File("facturacion.db");
        if(!base.exists()){
            InitDatabase crearBase = new InitDatabase();
        }
        new Login().setVisible(true);
        
    }    
}
