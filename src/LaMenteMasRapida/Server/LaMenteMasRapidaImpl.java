/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Server;

import LaMenteMasRapida.Interface.InterfaceMenteRapida;
import java.util.ArrayList;

public class LaMenteMasRapidaImpl implements InterfaceMenteRapida{

    ArrayList <Usuario> Usuarios;
    public ArrayList MasRapido;
    Integer respu;
    public static boolean yaHayPartida  = false;
    String[] vectorsig;
    Integer[] vectornum;
    String[] vectorres;
    Tiempo tiempo;
    Preguntas pregunt;
    public int i,t,p;
    String masrap="";
   
    public LaMenteMasRapidaImpl () {
      
        Usuarios = new ArrayList<Usuario>();
        MasRapido = new ArrayList();
        tiempo = new Tiempo(this);
        pregunt =new Preguntas();
        i=0;t=0;p=0;
    }
    
    @Override
    public boolean IniciarSesion(String nombre) {
        boolean agregado;
        boolean encontrado = false;
        if(Usuarios.isEmpty()){
            Usuarios.add(new Usuario(nombre));
            agregado = true;
        }else{
            for (int c = 0; c < Usuarios.size(); c++) {
                if(Usuarios.get(c).getNombre().equals(nombre)){
                    encontrado = true;
                    break;
                }
            }
            if(encontrado){
                agregado = false;
            }else{
                Usuarios.add(new Usuario(nombre));
                agregado = true;
            }
        }
        return agregado;
    }

    @Override
    public boolean CerrarSesion(String nombre) {
        boolean salir = false;
        for (int x = 0; x < Usuarios.size(); x++) {
            if(Usuarios.get(x).getNombre().equals(nombre)){
                Usuarios.remove(x);
                salir = true;
                break;
            }
        }
        return salir;
    }

    @Override
    public String CargarPregunta(int i) {
        String pregunta="";
        pregunta=pregunt.GetPregunta(i);
        if(tiempo.tiempo==0)
            i++;
        return pregunta;
    }
    
    @Override
    public String CargarRespuesta(int i) {
        String respuesta="";
            respuesta=pregunt.GetRespuesta(i);
        return respuesta;
    }

    @Override
    public String EnviarRespuesta(String nombre) {
        for (int r = 0; r < Usuarios.size(); r++) {
                if(Usuarios.get(r).getNombre().equals(nombre))
                    Usuarios.get(r).setPuntaje();
                masrap=""+Usuarios.get(r).getPuntaje();
            }
         return nombre+"--------------------> "+masrap;  
        /*if(nombre.equals("")){
            masrap=MasRapido.get(0).toString();
            if(!MasRapido.isEmpty())
                MasRapido.clear();
        }else{
            if(MasRapido.isEmpty()){
            MasRapido.add(nombre);
            for (int r = 0; r < Usuarios.size(); r++) {
                if(Usuarios.get(r).getNombre().equals(nombre))
                    Usuarios.get(r).setPuntaje();
                break;
            }masrap = nombre;
            }
        }return ""+masrap;*/
    }
    
    @Override
    public String LeerContactos() {
        String Conectados = "";
        for (int z = 0; z < Usuarios.size(); z++){
            Conectados = Conectados + Usuarios.get(z).getNombre() + ",";
        }
        return Conectados;
    }

    @Override
    public String SoyPrimero() {
        String primero="";

        if(!Usuarios.isEmpty()){
           primero=Usuarios.get(0).getNombre();
        }
        return primero;
    }

    @Override
    public void IniciarTiempo(String nombre) {
         if(Usuarios.get(0).getNombre().equals(nombre)){
            tiempo.EjecutarBloque(1);
            tiempo.start();
        }
    }

    @Override
    public String VerPuntaje(String nombre) {
        String puntaje = "";
        for (int w = 0; w < Usuarios.size(); w++){
            if(Usuarios.get(w).getNombre().equals(nombre)){
                puntaje = "" + Usuarios.get(w).Puntaje;
            }
        }
        return puntaje;
    }

    @Override
    public String ObtenerTiempo(String nombre) {
        return ""+tiempo.tiempo;
    }
    
    @Override
    public String Ganador () {
        int gaux=0;
        String Nombre = null;
        for(int y=0;y<Usuarios.size();y++){
        if(gaux<Usuarios.get(y).getPuntaje())
            gaux=Usuarios.get(y).getPuntaje();
            Nombre=Usuarios.get(y).getNombre();
        }
        return "El Ganador fue "+Nombre+" con "+gaux+" pts";
                
    }
    
    @Override
    public void DetenerHiloTiempo (int i) {
        tiempo.EjecutarBloque(i);
    }
    
    @Override
    public void ReiniciarHiloTiempo () {
        tiempo.Reiniciar();
    }
    
    @Override
    public void Sw(int i) {
        t=i;
    }
    
    @Override
    public int ObtenerSw() {
        return t;
    }
    
    @Override
    public void NumPreg(int i) {
            p++;
    }

    @Override
    public int ObtenerNumPreg() {
        return p;
    }
    
    //Prueba de los Metodos de esta Clase
    /*public static void main(String arg[]){
        try{
            LaMenteMasRapidaImpl Mente=new LaMenteMasRapidaImpl();
            System.out.println(""+Mente.IniciarSesion("ali"));
            Mente.IniciarTiempo("ali");
            System.out.println(""+Mente.EnviarRespuesta("ali"));
            System.out.println(""+Mente.EnviarRespuesta(""));
            System.out.println(""+Mente.tiempo.tiempo);
            
            Mente.tiempo.Reiniciar();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }*/

}