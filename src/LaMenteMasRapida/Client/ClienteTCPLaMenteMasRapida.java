/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Client;

import LaMenteMasRapida.Interface.InterfaceMenteRapida;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteTCPLaMenteMasRapida implements InterfaceMenteRapida{
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    
    public ClienteTCPLaMenteMasRapida(String host, int port) {
        try{
            socket=new Socket(InetAddress.getByName(host), port);
            out=new DataOutputStream(socket.getOutputStream());
            in=new DataInputStream(socket.getInputStream());
        }catch(Exception e){
            System.out.println("\tEl servidor no se esta ejecutandose");
         System.out.println("\t=============================");
        }
  }

    @Override
    public boolean IniciarSesion(String nombre) {
        return Boolean.parseBoolean(enviarMensajeSocket("IniciarSesion%.%"+nombre));
    }

    @Override
    public boolean CerrarSesion(String nombre) {
        return Boolean.parseBoolean(enviarMensajeSocket("CerrarSesion%.%"+nombre));
    }

    @Override
    public String CargarPregunta(int i) {
        return enviarMensajeSocket("CargarPregunta%.%"+i);
    }

    @Override
    public String CargarRespuesta(int i) {
        return enviarMensajeSocket("CargarRespuesta%.%"+i);
    }
    
    @Override
    public String EnviarRespuesta(String nombre) {
        return enviarMensajeSocket("EnviarRespuesta%.%"+nombre);
    }

    @Override
    public String LeerContactos() {
        return enviarMensajeSocket("LeerContactos%.%");
    }

    @Override
    public String SoyPrimero() {
        return enviarMensajeSocket("SoyPrimero%.%");
    }

    @Override
    public void IniciarTiempo(String nombre) {
        enviarMensajeSocket("IniciarTiempo%.%"+nombre);
    }

    @Override
    public String VerPuntaje(String nombre) {
        return enviarMensajeSocket("VerPuntaje%.%"+nombre);
    }

    @Override
    public String ObtenerTiempo(String nombre) {
        return enviarMensajeSocket("ObtenerTiempo%.%"+nombre);
    }
    
    @Override
    public String Ganador() {
        return enviarMensajeSocket("Ganador%.%");
    }
    
    @Override
    public void DetenerHiloTiempo (int i) {
        enviarMensajeSocket("DetenerHiloTiempo%.%"+i);
    }
    
    @Override
    public void ReiniciarHiloTiempo() {
        enviarMensajeSocket("ReiniciarHiloTiempo%.%");
    }

    @Override
    public void Sw(int i) {
        enviarMensajeSocket("Sw%.%"+i);
    }
    
    @Override
    public int ObtenerSw() {
        return Integer.parseInt(enviarMensajeSocket("ObtenerSw%.%"));
    }

    @Override
    public void NumPreg(int i) {
        enviarMensajeSocket("NumPreg%.%"+i);
    }

    @Override
    public int ObtenerNumPreg() {
        return Integer.parseInt(enviarMensajeSocket("ObtenerNumPreg%.%"));
    }    
    
     public String enviarMensajeSocket(String msg){
         System.out.println("intentare enviar el siguiente mensaje: "+msg);
                   try {
                    out.writeUTF(msg);
                    out.flush();
                    msg = in.readUTF();
                    System.out.println("recibi esta respuesta: "+msg);  
           } catch (IOException e) {}
             return msg;
     }
     
}
