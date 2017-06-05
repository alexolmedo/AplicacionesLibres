package Aplicacion;

import javax.swing.JProgressBar;

public class cargar extends Thread{ 
    JProgressBar progreso;
    public  cargar (JProgressBar progreso){
        super();
        this.progreso=progreso;
    }  
    public void run(){
        for(int i=1;i<=100;i++){
            
            progreso.setValue(i);
            
            //Velocidad del progress bar.
            try {
                Thread.sleep(15);
            } catch(Exception e) {
                
            }
        }
    }
    public void pausa(int mlSeg){
        try{
            Thread.sleep(mlSeg);
        }catch(Exception e){
            
        }
    } 
}