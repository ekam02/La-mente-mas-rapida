package LaMenteMasRapida.client;

import LaMenteMasRapida.Client.VentanaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloTiempoPreguntas extends Thread implements Runnable{

   String Nombre;
   VentanaPrincipal Cliente;
   
   public HiloTiempoPreguntas(VentanaPrincipal Cliente, String Nombre) {
        this.Cliente=Cliente;
        this.Nombre=Nombre;    
    }
    
    @Override
   public void run(){
    int i=0;
    
    while(true){
            Cliente.LlenarPregunta();
            Cliente.LlenarConectados();
            
           try {
                HiloTiempoPreguntas.sleep(1000);
            } catch (InterruptedException ex) {
                this.stop();
                Logger.getLogger(LaMenteMasRapida.client.HiloClienteLaMenteMasRapida.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
   }
}
