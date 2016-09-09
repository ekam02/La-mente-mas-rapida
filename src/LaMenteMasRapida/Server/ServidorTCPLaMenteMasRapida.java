/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Server;

import LaMenteMasRapida.Interface.InterfaceMenteRapida;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCPLaMenteMasRapida implements InterfaceMenteRapida{
    
    private InterfaceMenteRapida MenteRapida=null;
    private ServerSocket socketServidor = null;
    private Socket socketCliente = null;
    private LaMenteMasRapidaFactory factory = null;
   
        //Constructor
        public ServidorTCPLaMenteMasRapida(int port)
   	{
   		try {   MenteRapida = (InterfaceMenteRapida) new LaMenteMasRapidaImpl();
   			socketServidor = new ServerSocket(port);
   		} catch (IOException e) {
                    
                }
         }
   	
        
        public void daemon(){
            while (true) {
   			try {
   				System.out.println("Estoy esperando...");
   				socketCliente = socketServidor.accept();
                             	System.out.println("encontre un cliente");
   				factory = new LaMenteMasRapidaFactory(socketCliente,MenteRapida);
                                factory.start();
   			} catch (IOException e) {
                            System.out.println(""+e);
                        }
   		}
        }

public static void main(String arg[]){
    ServidorTCPLaMenteMasRapida server = new ServidorTCPLaMenteMasRapida(2012);
		server.daemon();
                
}

    @Override
    public boolean IniciarSesion(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean CerrarSesion(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String CargarPregunta(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String CargarRespuesta(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String EnviarRespuesta(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String LeerContactos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String SoyPrimero() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void IniciarTiempo(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String VerPuntaje(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String ObtenerTiempo(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
     
    @Override
    public String Ganador() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void DetenerHiloTiempo (int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void ReiniciarHiloTiempo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Sw(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int ObtenerSw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void NumPreg(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int ObtenerNumPreg() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

}
