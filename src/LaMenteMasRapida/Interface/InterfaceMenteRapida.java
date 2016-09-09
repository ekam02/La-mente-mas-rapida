/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Interface;

public interface InterfaceMenteRapida {
    public boolean IniciarSesion (String nombre);
    public boolean CerrarSesion (String nombre);
    public String CargarPregunta (int i);
    public String CargarRespuesta (int i);
    public String EnviarRespuesta (String nombre);
    public String LeerContactos ();
    public String SoyPrimero();
    public void IniciarTiempo(String nombre);
    public String VerPuntaje(String nombre);
    public String ObtenerTiempo(String nombre);
    public String Ganador();
    public void DetenerHiloTiempo (int i);
    public void ReiniciarHiloTiempo ();
    public void Sw (int i);
    public int ObtenerSw ();
    public void NumPreg (int i);
    public int ObtenerNumPreg ();
}
