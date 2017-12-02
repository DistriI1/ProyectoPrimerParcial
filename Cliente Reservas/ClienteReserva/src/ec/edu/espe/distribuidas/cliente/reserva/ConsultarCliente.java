/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.reserva;

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
public class ConsultarCliente extends javax.swing.JFrame {

    Cabecera cabecera=new Cabecera();
    Cliente cliente=new Cliente();
    
        
    String tipoMensaje="RQ";
    String originador="RESE";
    String idMensaje="BUSCLIENTE";
    
    public ConsultarCliente() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    private static final Logger LOG = Logger.getLogger(Start.class.getName());
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarR = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(451, 485));
        setName("Frame CheckOut"); // NOI18N
        setUndecorated(true);
        getContentPane().setLayout(null);

        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(370, 4, 70, 20);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Solicitar Cliente");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 120, 15);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingrese el codigo del cliente");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(130, 100, 170, 15);
        jLabel4.getAccessibleContext().setAccessibleName("Ingrese el código del Cliente ");
        jLabel4.getAccessibleContext().setAccessibleDescription("");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel2.setText("Reservas");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 50, 80, 15);

        btnBuscarR.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        btnBuscarR.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/check/out/iconos/magnifier1.png"))); // NOI18N
        btnBuscarR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarRMouseClicked(evt);
            }
        });
        getContentPane().add(btnBuscarR);
        btnBuscarR.setBounds(290, 120, 30, 30);

        txtCodigoCliente.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        txtCodigoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCodigoCliente.setSelectionColor(new java.awt.Color(0, 153, 153));
        txtCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoClienteActionPerformed(evt);
            }
        });
        txtCodigoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoClienteKeyTyped(evt);
            }
        });
        getContentPane().add(txtCodigoCliente);
        txtCodigoCliente.setBounds(130, 120, 190, 30);

        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "TIPO DE IDENTIFICACIÓN ", "IDENTIFICACIÓN", "NOMBRE", "PAÍS", "DIRECCIÓN", "TELÉFONO", "CORREO"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 180, 410, 210);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/check/out/iconos/FondoCheckOut.PNG"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, -20, 460, 520);

        jTable2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "TIPO IDENTIFICACIÓN", "IDENTIFICACIÓN", "NOMBRE", "PAÍS", "DIRECCIÓN", "TELÉFONO", "CORREO"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 180, 410, 210);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoClienteKeyPressed

    private void txtCodigoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoClienteKeyTyped
        // TODO add your handling code here:
        String Caracteres = txtCodigoCliente.getText(); 
        if(Caracteres.length()>=15){ 
            evt.consume(); 
        } 
    }//GEN-LAST:event_txtCodigoClienteKeyTyped

    private void txtCodigoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoClienteActionPerformed

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnBuscarRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarRMouseClicked

        try{
          
            String cabeceraMensaje="";
            String server= "192.168.1.114";
            String cuerpo="";
            String mensaje="";
            Integer port=2000;
            SocketClienteReserva client = new SocketClienteReserva(server, port);
            //client.connect();
            
            cuerpo=cliente.consulta(txtCodigoCliente);
            cabeceraMensaje=cabecera.getCabecera(tipoMensaje, originador, 
                    idMensaje, cuerpo,cuerpo);
            mensaje=cabecera.getMensaje(cabeceraMensaje, cuerpo);
            System.out.println(mensaje);
            client.send(mensaje);
            
      }catch(IOException e)
        {
            LOG.log(Level.SEVERE,"Ocurrio un error",e);
        }
 
    }//GEN-LAST:event_btnBuscarRMouseClicked

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
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBuscarR;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtCodigoCliente;
    // End of variables declaration//GEN-END:variables
}
