/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Server;
import LaMenteMasRapida.Interface.InterfaceMenteRapida;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LaMenteMasRapidaFactory extends Thread implements Runnable{
     private InterfaceMenteRapida MenteRapida = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private Socket socketCliente = null;
    private String vec[];
    private String retval,cmd;
     
    public LaMenteMasRapidaFactory (Socket socketCliente, InterfaceMenteRapida MenteRapida){
     this.socketCliente = socketCliente;
        
        this.MenteRapida = MenteRapida; 
        try {
            in = new DataInputStream(socketCliente.getInputStream());
            out = new DataOutputStream(socketCliente.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    public String demux(String msg) {                        
        vec = msg.split("%.%");
        retval="OK";
        System.out.println(vec[0]);
        try {
            if(vec[0].equals("IniciarSesion")){
                retval=""+MenteRapida.IniciarSesion(vec[1]);
                }else if(vec[0].equals("CerrarSesion")){
                    MenteRapida.CerrarSesion(vec[1]);
                    retval="Salir";
                }else if(vec[0].equals("LeerContactos")){
                    retval=MenteRapida.LeerContactos();
                }else if(vec[0].equals("CargarPregunta")){
                    retval=MenteRapida.CargarPregunta(Integer.parseInt(vec[1]));
                }else if(vec[0].equals("CargarRespuesta")){
                    retval=MenteRapida.CargarRespuesta(Integer.parseInt(vec[1]));
                }else if(vec[0].equals("EnviarRespuesta")){
                    retval = "" + MenteRapida.EnviarRespuesta(vec[1]+"");
                }else if(vec[0].equals("SoyPrimero")){
                    retval=MenteRapida.SoyPrimero();                         
                }else if(vec[0].equals("IniciarTiempo")){
                    MenteRapida.IniciarTiempo(vec[1]);
                }else if(vec[0].equals("VerPuntaje")){
                    retval=MenteRapida.VerPuntaje(vec[1]);
                }else if(vec[0].equals("ObtenerTiempo")){
                    retval = "" + MenteRapida.ObtenerTiempo("");
                }else if(vec[0].equals("VerPuntaje")){
                    retval = "" + MenteRapida.VerPuntaje(vec[1]);
                }else if(vec[0].equals("Ganador")){
                    retval = "" + MenteRapida.Ganador();
                }else if(vec[0].equals("DetenerHiloTiempo")){
                    MenteRapida.DetenerHiloTiempo(Integer.parseInt(vec[1]));
                }else if(vec[0].equals("ReiniciarHiloTiempo")){
                    MenteRapida.ReiniciarHiloTiempo();
                }else if(vec[0].equals("Sw")){
                    MenteRapida.Sw(Integer.parseInt(vec[1]));
                }else if(vec[0].equals("ObtenerSw")){
                    retval = "" + MenteRapida.ObtenerSw();
                }else if(vec[0].equals("NumPreg")){
                    MenteRapida.NumPreg(Integer.parseInt(vec[1]));
                }else if(vec[0].equals("ObtenerNumPreg")){
                    retval = "" + MenteRapida.ObtenerNumPreg();
                }else
                retval = "No entro en ninguna funcion.";

    } catch (Exception e) {
    }
        return retval;
    }  
    
    
    @Override
   public void run() {
       
        cmd = "NEW";
        
        //System.out.println("run...");
        try {
        for(; !cmd.equals("Salir"); out.flush())
        {
            cmd = in.readUTF();
            System.out.println("llego mensaje "+cmd);
            System.out.println("envresp "+retval);
            // retval = demux(cmd);
            out.writeUTF(demux(cmd));
        }
        socketCliente.close();
        } catch (IOException e) {
                System.out.println("\tEl cliente "+socketCliente.getLocalSocketAddress()+" cerro la conexion\n\t==============================");
        }

    }
}
