/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Server;

import java.util.ArrayList;
import java.util.Random;


public class Preguntas {
    ArrayList <pregunta> preguntas = new ArrayList();
   public class pregunta{
       String NombrePregunta;
       String OpcionA;
       String OpcionB;
       String OpcionC;
       String OpcionD;
       String Respuesta;
       ArrayList operadores =new ArrayList();
       
       
     public pregunta(String  nomb){
       String resp[];
       resp=nomb.split("=");
       NombrePregunta=resp[0];
       Respuesta=resp[1];
     }
     
   }
   public String GenerarPre() {
           
        String pregunta="";
        Random aleatorio = new Random();
        Integer i=0;
        Integer j=0;
        Integer k=0;
        String y="";
        Integer num;
        Integer h=0;
         String[] vectorsig;
         Integer[] vectornum;
         String[] vectorres;
        
        while(i<=1){
        i = aleatorio.nextInt(4);
        }

        vectorsig = new String[i];
        vectornum = new Integer[i+1];
        vectorres = new String[i*2+1];
    
        j = (aleatorio.nextInt(30))-10;
        vectornum[0]=j;
        pregunta=String.valueOf(j);
        
        for (int l = 1; l <= i; l++) {
            k = aleatorio.nextInt(3);
            j = (aleatorio.nextInt(30))-10;
            y=String.valueOf(j);
           
            if(k==0){
                if(j<0){
                  vectorsig[l-1]="+"; 
                  vectornum[l]=j;
                  pregunta+=y;  
                    
                }else{
                  vectorsig[l-1]="+";
                  pregunta+="+";
                  vectornum[l]=j;
                  pregunta+=y;  
                }
            }
            if(k==1){
                if(j<0){
                   vectorsig[l-1]="*";
                   pregunta+="*";
                   pregunta+="("+y+")";
                   vectornum[l]=j;
                }
                else{
                  vectorsig[l-1]="*";
                  pregunta+="*";
                  vectornum[l]=j;
                  pregunta+=y;  
                }
            }
            if(k==2){
                
                if(j<0){
                  vectorsig[l-1]="+"; 
                  vectornum[l]=j;
                  pregunta+=y;  
                    
                }else{
                  vectorsig[l-1]="-";
                  pregunta+="-";
                  vectornum[l]=j;
                  pregunta+=y;  
                }
               
            } 
         }
        
        vectorres[h]=String.valueOf(vectornum[0]);
        h++;
       
         for (int l = 0; l < vectorsig.length; l++) { 
              if("*".equals(vectorsig[l])){
                 if(l>0){
                    if(vectorsig[l-1].equals(vectorsig[l])){
                      num=Integer.parseInt(vectorres[h-1]);
                      num=num*vectornum[l+1];
                      vectorres[h-1]=String.valueOf(num);
                      vectornum[l+1]=num;
                    }
                    else{
                      num = vectornum[l]* vectornum[l+1];
                      vectorres[h-1]=String.valueOf(num);
                      vectornum[l+1]=num;
                    }
                 }  
                 else{
                    num = vectornum[l]* vectornum[l+1];
                    vectorres[h-1]=String.valueOf(num);
                    vectornum[l+1]=num;
                 }
              }
              else{
                 vectorres[h]=vectorsig[l];
                 h++;
                 vectorres[h]=String.valueOf(vectornum[l+1]);
                 h++;
              }
              
  
          }
         
          num=0;
          num=Integer.parseInt(vectorres[0]);
        
          for (int l = 0; l <h;l++) {
            if("+".equals(vectorres[l])){
              num+=Integer.parseInt(vectorres[l+1]); 
            }
            if("-".equals(vectorres[l])){
              num-=Integer.parseInt(vectorres[l+1]); 
            }       
          }
         
          return pregunta+"="+num;
    
    }
   
        pregunta pregunta_1= new pregunta(GenerarPre());
        pregunta pregunta_2= new pregunta(GenerarPre());
        pregunta pregunta_3= new pregunta(GenerarPre());
        pregunta pregunta_4= new pregunta(GenerarPre());
        pregunta pregunta_5= new pregunta(GenerarPre());
        pregunta pregunta_6= new pregunta(GenerarPre());
        pregunta pregunta_7= new pregunta(GenerarPre());
        pregunta pregunta_8= new pregunta(GenerarPre());
        pregunta pregunta_9= new pregunta(GenerarPre());
        pregunta pregunta_10= new pregunta(GenerarPre());
   
   public Preguntas(){        
          preguntas.add(pregunta_1);
          preguntas.add(pregunta_2);
          preguntas.add(pregunta_3);
          preguntas.add(pregunta_4);
          preguntas.add(pregunta_5);
          preguntas.add(pregunta_6);
          preguntas.add(pregunta_7);
          preguntas.add(pregunta_8);
          preguntas.add(pregunta_9);
          preguntas.add(pregunta_10);    
   }

   public String GetPregunta(int i){
       //System.out.println(""+preguntas.get(i).Respuesta);
         return preguntas.get(i).NombrePregunta;  
     }
     public String GetOpciones(int i){
         //System.out.println(""+preguntas.get(i).OpcionA+","+preguntas.get(i).OpcionB+","+preguntas.get(i).OpcionC+","+preguntas.get(i).OpcionD);
         return preguntas.get(i).OpcionA+","+preguntas.get(i).OpcionB+","+preguntas.get(i).OpcionC+","+preguntas.get(i).OpcionD;  
     }
     public String GetRespuesta(int i){
         //System.out.println(""+preguntas.get(i).Respuesta);
         return preguntas.get(i).Respuesta;  
     }
    
    /* public static void main(String[] args) {
        Preguntas preg =new Preguntas();
        for(int i=0;i<10;i++){
            System.out.println("\t"+preg.GetPregunta(i));
            System.out.println("\t"+preg.GetRespuesta(i));
            System.out.println("0=================================0");
        }
        
    }*/
}
