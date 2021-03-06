/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LaMenteMasRapida.Client;

import LaMenteMasRapida.Interface.InterfaceMenteRapida;
import LaMenteMasRapida.Server.LaMenteMasRapidaImpl;
import LaMenteMasRapida.Server.Tiempo;
import LaMenteMasRapida.client.HiloClienteLaMenteMasRapida;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends javax.swing.JFrame {

    public ArrayList conectados;
    public ArrayList conectadosOld;
    public InterfaceMenteRapida prueba;
    public LaMenteMasRapidaImpl MenteRapida;
    public String Nombre, Host,OldPreg;
    public int Puerto,j,t;
    public boolean sesion;
    HiloClienteLaMenteMasRapida Hilo;
    Tiempo Tiempo;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal(String nombre, String host, int port) {
        j=0;t=8;
        conectadosOld=new ArrayList();
        conectados=new ArrayList();
        Nombre=nombre;
        Host=host;
        Puerto=port;
        sesion=true;
        setTitle(Nombre);
        prueba= new LaMenteMasRapidaImpl();
        
        initComponents();
           
        if(host==null){
            prueba=new LaMenteMasRapidaImpl();
        }else try {
            prueba = new ClienteTCPLaMenteMasRapida(host, port);
            while(sesion){
                if(!prueba.IniciarSesion(Nombre)){
                    JOptionPane.showMessageDialog(null, "Nombre ya exixte.\nEscriba otro Nombre.");    
                    Nombre=JOptionPane.showInputDialog("Nombre: ","Usuario");
                }else {
                    sesion=false;
                    Hilo=new HiloClienteLaMenteMasRapida(this,Nombre); // crea el hilo
                    Hilo.start();
                    if(!Nombre.equals(prueba.SoyPrimero())){
                        IniciarJuego.setVisible(false);
                        
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    public void LlenarPregunta(){
        if(prueba.ObtenerSw()==10)
            lblTiempo.setText("Tiempo restante: "+(10-Integer.parseInt(prueba.ObtenerTiempo(Nombre)))/*+"  -->  "+prueba.CargarRespuesta(prueba.ObtenerNumPreg()-1)*/);
        if(prueba.ObtenerNumPreg()==10){
            lblEtiquetaMenteRapida.setText("Fin del Juego.");
            lblMenteRapida.setText(""+prueba.Ganador());
            prueba.DetenerHiloTiempo(0);
            IniciarJuego.setEnabled(true);
        }
        if("0".equals(prueba.ObtenerTiempo(Nombre)) && prueba.ObtenerSw()==10){
            lblEtiquetaPregunta.setText("Pregunta "+(prueba.ObtenerNumPreg()+1)+": ");
            Pregunta.setText(prueba.CargarPregunta(prueba.ObtenerNumPreg()));
            lblRespuesta.setText("");
            Responder.setEnabled(true);
        }
    }

    public void LlenarConectados(){
        String Conectados="",ListaConectados[]=null, puntos="";
        Conectados=prueba.LeerContactos();
        ListaConectados=Conectados.split(",");
        Conectados="";
        conectados.clear();
        conectados.addAll(Arrays.asList(ListaConectados));
        System.out.println(""+conectados);
        if(!ListaConectados.equals(Nombre+",") && !ListaConectados.equals(""))
        for(String s:ListaConectados){
                puntos=prueba.VerPuntaje(s);
                Conectados+=s+" --->  "+puntos+" pts\n";
        }
        jTextAreaUsuarios.setText(Conectados);
        ArrayList salieron=new ArrayList();
        ArrayList entraron=new ArrayList();
        
        Conectados=prueba.LeerContactos();
        //System.out.println("conectados ->  "+Conectados);
        ListaConectados=Conectados.split(",");
        Conectados="";
        //System.out.println("array ------> "+conectados);
        //System.out.println("arrayOld ---> "+conectadosOld);
        //bustcar modificaciones en lalista de conecados
            entraron.clear();
            if(!conectadosOld.isEmpty() && !conectados.isEmpty()){
            for(int i=0;i<conectados.size();i++){
                if(!conectadosOld.contains(conectados.get(i)))        
                        entraron.add(conectados.get(i));
            }
            salieron.clear();
            if(!conectados.isEmpty() && !conectadosOld.isEmpty())
            for(int i=0;i<conectadosOld.size();i++){
                    if(!conectados.contains(conectadosOld.get(i)))
                        salieron.add(conectadosOld.get(i));
            }
            if(!entraron.isEmpty()){
                for(int i=0;i<entraron.size();i++)
                JOptionPane.showMessageDialog(rootPane, " se ha conectado "+entraron.get(i));
            }

            if(!salieron.isEmpty()){
                for(int i=0;i<salieron.size();i++)
                    if(!salieron.get(i).equals(Nombre) && !salieron.get(i).equals(""))
                JOptionPane.showMessageDialog(rootPane, " se ha desconectado "+salieron.get(i));
            }
        }
        conectadosOld.clear();
        
        for(int i=0;i<conectados.size();i++){
            conectadosOld.add(conectados.get(i));
            //System.out.println("conectadosOld-----------------------> "+conectadosOld);
        }
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblEtiquetaPregunta = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Pregunta = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaUsuarios = new javax.swing.JTextArea();
        Responder = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        IniciarJuego = new javax.swing.JButton();
        Respuesta = new javax.swing.JTextField();
        lblEtiquetaMenteRapida = new javax.swing.JLabel();
        lblMenteRapida = new javax.swing.JLabel();
        lblRespuesta = new javax.swing.JLabel();
        lblTiempo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setText("La Mente Mas Rapida");

        lblEtiquetaPregunta.setText("Pregunta:");

        jLabel4.setText("Escriba respuesta:");

        jLabel6.setText("Usuarios");

        jTextAreaUsuarios.setColumns(20);
        jTextAreaUsuarios.setEditable(false);
        jTextAreaUsuarios.setRows(5);
        jTextAreaUsuarios.setFocusable(false);
        jScrollPane2.setViewportView(jTextAreaUsuarios);

        Responder.setText("Responder");
        Responder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResponderActionPerformed(evt);
            }
        });

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        IniciarJuego.setText("Iniciar Juego");
        IniciarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarJuegoActionPerformed(evt);
            }
        });

        lblEtiquetaMenteRapida.setText(" ");

        lblMenteRapida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMenteRapida.setForeground(new java.awt.Color(0, 0, 204));

        lblRespuesta.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblRespuesta.setForeground(new java.awt.Color(255, 0, 0));
        lblRespuesta.setText(" ");

        lblTiempo.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        lblTiempo.setForeground(new java.awt.Color(255, 0, 0));
        lblTiempo.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Responder, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IniciarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(Respuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMenteRapida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEtiquetaMenteRapida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEtiquetaPregunta)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblEtiquetaMenteRapida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMenteRapida, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEtiquetaPregunta)
                        .addGap(7, 7, 7)
                        .addComponent(Pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Respuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Responder)
                            .addComponent(IniciarJuego))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salir)
                    .addComponent(lblTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ResponderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResponderActionPerformed
        if(prueba.CargarRespuesta(prueba.ObtenerNumPreg()-1).equals(Respuesta.getText())){
            prueba.EnviarRespuesta(Nombre+"");
            lblRespuesta.setText("Correcto!!");
            prueba.ReiniciarHiloTiempo();
            
            
        }else
            lblRespuesta.setText("Incorrecto!!");
        Respuesta.setText("");
        Responder.setEnabled(false);
    }//GEN-LAST:event_ResponderActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        String conectados[]=null;
        prueba.CerrarSesion(Nombre);
        JOptionPane.showMessageDialog(null, "Sesión cerrada con exito.");
        conectados=prueba.LeerContactos().split(",");
        if(conectados.length==0){
            prueba.DetenerHiloTiempo(0);
        }
        Hilo.stop();
        this.dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String conectados[]=null;
        prueba.CerrarSesion(Nombre);
        JOptionPane.showMessageDialog(null, "Sesión cerrada con exito.");
        conectados=prueba.LeerContactos().split(",");
        if(conectados.length==0){
            prueba.DetenerHiloTiempo(0);
        }
        Hilo.stop();
    }//GEN-LAST:event_formWindowClosing

    private void IniciarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarJuegoActionPerformed
        if(prueba.ObtenerSw()!=10)
            prueba.IniciarTiempo(Nombre);
        else
            prueba.DetenerHiloTiempo(1);
        IniciarJuego.setEnabled(false);
        prueba.Sw(10);
    }//GEN-LAST:event_IniciarJuegoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                String nombre =JOptionPane.showInputDialog("Nombre: ","");
                String host =JOptionPane.showInputDialog("Host: ","localhost");
                new VentanaPrincipal(nombre,host, 2012).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IniciarJuego;
    private javax.swing.JLabel Pregunta;
    private javax.swing.JButton Responder;
    private javax.swing.JTextField Respuesta;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaUsuarios;
    private javax.swing.JLabel lblEtiquetaMenteRapida;
    private javax.swing.JLabel lblEtiquetaPregunta;
    private javax.swing.JLabel lblMenteRapida;
    private javax.swing.JLabel lblRespuesta;
    private javax.swing.JLabel lblTiempo;
    // End of variables declaration//GEN-END:variables
}