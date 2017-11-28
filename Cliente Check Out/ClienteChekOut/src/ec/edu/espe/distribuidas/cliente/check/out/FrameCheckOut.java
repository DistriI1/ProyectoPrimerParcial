/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.check.out;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.lt;
import javax.swing.JOptionPane;

/**
 *
 * @author DAVID
 */
public class FrameCheckOut extends javax.swing.JFrame {

    /**
     * Creates new form FrameCheckOut
     */
    public FrameCheckOut() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    SocketCheckOut sock = new SocketCheckOut();
    private static final Logger LOG = Logger.getLogger(Start.class.getName());
    private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
    public String longitudCuerpo(String longitud){
        int numero = 0;
        String longi="";
        char[] arrayChar = longitud.toCharArray();
		for(int i=0; i<arrayChar.length; i++){
			numero++;
                        
		}
                if(numero<=9){
                    longi = "000" + numero;
                }
                else if(numero>9)
                {
                    longi="00"+numero;
                }
                return longi;
    }
    public String cabecera(String Codigo){
        String tipoMensaje = "RQ";
        String originador = "CHON";
        String idMensaje = "FACTCONCLI";
        String cuerpoLongitud = longitudCuerpo(Codigo);
        return tipoMensaje+originador+fechaHora()+idMensaje+cuerpoLongitud+hash(Codigo)+Codigo;
    }
    public  String hash(String stringAEncriptar)
    {
        try
        {
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++)
           {
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
    public String fechaHora()
    {
        
        String fecha  = "";
        String añoS="";
        String mesS="";
        String diaS="";
        String horaS="";
        String minutosS="";
        String segundosS="";
        Calendar calendario = Calendar.getInstance();
        añoS= String.valueOf(calendario.get(Calendar.YEAR));
        mesS=String.valueOf(calendario.get(Calendar.MONTH));
        diaS=String.valueOf(calendario.get(Calendar.DAY_OF_MONTH));
        horaS =String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        minutosS =String.valueOf(calendario.get(Calendar.MINUTE));
        segundosS =String.valueOf(calendario.get(Calendar.SECOND));
        fecha=añoS+mesS+diaS+horaS+minutosS+segundosS;
        return fecha;
    }
    public void recibirRespuesta(String peticion){
        String tipoMensajeR = "";
        String originadorR = "";
        String idMensajeR = "";
        String fechaHoraR= "";
        String longitudR = "";
        String hashR = "";
        String respuesta = "";
      //  String cabecera = tipoMensaje+originador+fechaHora()+idMensaje;
        Integer longitud = 4;
        char[] arrayChar = peticion.toCharArray();
        for(int i=0; i<arrayChar.length; i++){
            if(i<=1){
                tipoMensajeR += arrayChar[i];
            }
            
            if(i<=5&&i>1){
                originadorR += arrayChar[i];
            }
           
            if(i<=19&&i>1&&i>5){
                fechaHoraR += arrayChar[i];
            }
            if(i<=29&&i>1&&i>5&&i>19){
                idMensajeR += arrayChar[i];
            }
            if(i<=33&&i>1&&i>5&&i>19&&i>29){
                longitudR += arrayChar[i];
            }
            if(i<=64&&i>1&&i>5&&i>19&&i>29&&i>33){
                hashR += arrayChar[i];
            }
            if(i>64){
                respuesta += arrayChar[i];
            }
        }
        JOptionPane.showMessageDialog(null, "TIPO DE MENSAJE " + tipoMensajeR);
        JOptionPane.showMessageDialog(null, "ORIGINADOR " + originadorR);
        JOptionPane.showMessageDialog(null, "FECHA Y HORA " + fechaHoraR);
        JOptionPane.showMessageDialog(null, "ID MENSAJE " + idMensajeR);
        JOptionPane.showMessageDialog(null, "LONGITUD " + longitudR);
        JOptionPane.showMessageDialog(null, "CODIGO HASH " + hashR);
        JOptionPane.showMessageDialog(null, "RESPUESTA " + respuesta);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoReserva = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(451, 485));
        setMinimumSize(new java.awt.Dimension(451, 485));
        setName("Frame CheckOut"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(451, 485));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingrese el codigo de la reserva");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 140, 170, 15);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel2.setText("Check Out");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 50, 80, 15);

        txtCodigoReserva.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        txtCodigoReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCodigoReserva.setSelectionColor(new java.awt.Color(0, 153, 153));
        txtCodigoReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoReservaActionPerformed(evt);
            }
        });
        txtCodigoReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoReservaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoReservaKeyTyped(evt);
            }
        });
        getContentPane().add(txtCodigoReserva);
        txtCodigoReserva.setBounds(130, 160, 190, 30);

        btnBuscar.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/check/out/iconos/search.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(170, 210, 100, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 260, 410, 210);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/check/out/iconos/FondoCheckOut.PNG"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, -20, 460, 520);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoReservaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoReservaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoReservaKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String Codigo = "";
        try{
            String server= "192.168.1.114";
            Integer port=2000;
            SocketCheckOut client = new SocketCheckOut(server, port);
            client.connect();
            Codigo = txtCodigoReserva.getText();
           // client.send(cabecera(Codigo));
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente " + cabecera(Codigo));
            recibirRespuesta("RPSERV20171122234559FACTCONCLI00083867994d1be6e111e067ac5dbf3ae3cOKK&");
          //  recibirRespuesta(cabecera(Codigo));
            
        }catch(IOException e){
         LOG.log(Level.SEVERE,"Ocurrio un error",e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtCodigoReservaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoReservaKeyTyped
        // TODO add your handling code here:
        String Caracteres = txtCodigoReserva.getText(); 
        if(Caracteres.length()>=10){ 
            evt.consume(); 
        } 
    }//GEN-LAST:event_txtCodigoReservaKeyTyped

    private void txtCodigoReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoReservaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameCheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCodigoReserva;
    // End of variables declaration//GEN-END:variables
}
