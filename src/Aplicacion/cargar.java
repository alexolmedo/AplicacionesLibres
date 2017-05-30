/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import javax.swing.JProgressBar;

/**
 *
 * @author Gabriela
 */
public class cargar extends Thread{
    
    JProgressBar progreso;
    public  cargar (JProgressBar progreso){
        super();
        this.progreso=progreso;
    }
    
    public void run(){
        for(int i=1;i<=100;i++){
            progreso.setValue(i);
            pausa(50);
        }
    }
    
    public void pausa(int mlSeg){
        try{
            Thread.sleep(mlSeg);
        }catch(Exception e){
            
        }
    }
    
}
