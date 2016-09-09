/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.client;

import LaMenteMasRapida.Client.VentanaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloClienteLaMenteMasRapida extends Thread implements Runnable{

   String Nombre;
   VentanaPrincipal Cliente;
   int iterador;
   
   public HiloClienteLaMenteMasRapida(VentanaPrincipal Cliente, String Nombre) {
        this.Cliente=Cliente;
        this.Nombre=Nombre;    
        iterador=1;
    }
    
    @Override
   public void run(){
    while(true){
        Cliente.LlenarConectados();
        Cliente.LlenarPregunta();
        
        try {
            HiloClienteLaMenteMasRapida.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloClienteLaMenteMasRapida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   }
}
