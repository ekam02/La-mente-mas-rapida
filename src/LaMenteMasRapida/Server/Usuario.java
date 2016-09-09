/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Server;

public class Usuario {

    String nombre;
    public int Puntaje=0;


     public Usuario(String nombre)
    {
        this.nombre = nombre;
    }

    public int getPuntaje() 
    {
        return Puntaje;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setPuntaje()
    {
        this.Puntaje++;
    }

}
