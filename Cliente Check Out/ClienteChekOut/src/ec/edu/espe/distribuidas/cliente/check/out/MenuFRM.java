/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.check.out;

/**
 *
 * @author DAVID
 */
public class MenuFRM extends javax.swing.JFrame {

    /**
     * Creates new form MenuFRM
     */
    public MenuFRM() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReporteConsolidado = new javax.swing.JButton();
        btnSolicitarFactura = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(434, 565));
        setMinimumSize(new java.awt.Dimension(434, 565));
        setPreferredSize(new java.awt.Dimension(434, 565));
        getContentPane().setLayout(null);

        btnReporteConsolidado.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnReporteConsolidado.setText("Reporte Consolidado");
        btnReporteConsolidado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteConsolidadoActionPerformed(evt);
            }
        });
        getContentPane().add(btnReporteConsolidado);
        btnReporteConsolidado.setBounds(130, 220, 180, 50);

        btnSolicitarFactura.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnSolicitarFactura.setText("Solicitar Factura");
        btnSolicitarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarFacturaActionPerformed(evt);
            }
        });
        getContentPane().add(btnSolicitarFactura);
        btnSolicitarFactura.setBounds(150, 150, 140, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/check/out/iconos/FondoMenuPrincipal.PNG"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 452, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSolicitarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarFacturaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        SolicitarFacturaFRM solicitarFactura = new SolicitarFacturaFRM();
        solicitarFactura.setVisible(true);
    }//GEN-LAST:event_btnSolicitarFacturaActionPerformed

    private void btnReporteConsolidadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteConsolidadoActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        ConsolidarReporteFRM reporteConsolidado = new ConsolidarReporteFRM();
        reporteConsolidado.setVisible(true);
    }//GEN-LAST:event_btnReporteConsolidadoActionPerformed

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
            java.util.logging.Logger.getLogger(MenuFRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuFRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuFRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuFRM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporteConsolidado;
    private javax.swing.JButton btnSolicitarFactura;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
