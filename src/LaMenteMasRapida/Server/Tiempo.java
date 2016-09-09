/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Server;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Tiempo extends Thread implements Runnable {
    
    private boolean vida;
    private int ejecutarbloque;
    public int tiempo;
    public LaMenteMasRapidaImpl mente;

    public Tiempo(LaMenteMasRapidaImpl mente)
    {
        this.vida = true;
        this.tiempo = 0;
        this.mente=mente;
    }
    
    public void run(){
        while(vida){
            if(ejecutarbloque == 1){
               if(tiempo == 1)
                    mente.NumPreg(tiempo);
               if(tiempo <= 10){
                    try{
                        System.out.println(""+(10-tiempo));
                        Thread.sleep(1000);
                        tiempo++;
                    }catch (InterruptedException ex) {
                        Logger.getLogger(Tiempo.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }else
                   tiempo = 0;
            }else
                vida = false;
        }
    }
    
    public void EjecutarBloque(int a){
        this.ejecutarbloque = a;
    }
    public void Reiniciar(){
        this.tiempo = 0;
    }

}
