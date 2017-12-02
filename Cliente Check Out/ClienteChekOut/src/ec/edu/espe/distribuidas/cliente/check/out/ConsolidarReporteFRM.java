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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAVID
 */
public class ConsolidarReporteFRM extends javax.swing.JFrame {

    /**
     * Creates new form FrameCheckOut
     */
    public ConsolidarReporteFRM() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    private Peticion peticion= new Peticion();
    private Respuesta respuesta = new Respuesta();
    private static final Logger LOG = Logger.getLogger(Start.class.getName());
   

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegresar = new javax.swing.JButton();
        btnBuscarReserva1 = new javax.swing.JButton();
        btnSalir = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigoTour = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(449, 555));
        setName("Frame CheckOut"); // NOI18N
        getContentPane().setLayout(null);

        btnRegresar.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/check/out/iconos/reply.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar);
        btnRegresar.setBounds(100, 210, 110, 30);

        btnBuscarReserva1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        btnBuscarReserva1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/check/out/iconos/magnifier1.png"))); // NOI18N
        btnBuscarReserva1.setText("Buscar");
        btnBuscarReserva1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarReserva1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarReserva1);
        btnBuscarReserva1.setBounds(220, 210, 110, 30);

        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(370, 4, 70, 20);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingrese el codigo del Tour");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(140, 140, 170, 15);

        txtCodigoTour.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        txtCodigoTour.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCodigoTour.setSelectionColor(new java.awt.Color(0, 153, 153));
        txtCodigoTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoTourActionPerformed(evt);
            }
        });
        txtCodigoTour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoTourKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoTourKeyTyped(evt);
            }
        });
        getContentPane().add(txtCodigoTour);
        txtCodigoTour.setBounds(130, 160, 190, 30);

        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE TOUR", "TOTAL DE CONSUMOS"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 260, 410, 210);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/check/out/iconos/FondoConsolidarReporte.PNG"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, -10, 440, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoTourKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoTourKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoTourKeyPressed

    private void txtCodigoTourKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoTourKeyTyped
        // TODO add your handling code here:
        String Caracteres = txtCodigoTour.getText(); 
        if(Caracteres.length()>=10){ 
            evt.consume(); 
        } 
    }//GEN-LAST:event_txtCodigoTourKeyTyped

    private void txtCodigoTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoTourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoTourActionPerformed

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        MenuFRM obj = new MenuFRM();
         this.setVisible(false);
         obj.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarReserva1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarReserva1ActionPerformed
        // TODO add your handling code here:
        try{
            String Codigo = "";
            SocketCheckOut client = new SocketCheckOut();
            client.connect();
            Codigo = txtCodigoTour.getText();
            client.send(peticion.cabecera(Codigo,"REPVENTOUR"));
          
            String message = client.read();
          
            respuesta.recibirRespuesta(message, jTable1, txtCodigoTour);
            
        }catch(IOException e){
         LOG.log(Level.SEVERE,"Ocurrio un error",e);
        }

    }//GEN-LAST:event_btnBuscarReserva1ActionPerformed

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
            java.util.logging.Logger.getLogger(ConsolidarReporteFRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsolidarReporteFRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsolidarReporteFRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsolidarReporteFRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsolidarReporteFRM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarReserva1;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCodigoTour;
    // End of variables declaration//GEN-END:variables
}
