/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.reserva;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static javax.management.Query.gt;
import static javax.management.Query.lt;



public class ClienteReservaGrafico extends javax.swing.JFrame {

    private static final Logger LOG = Logger.getLogger(Start.class.getName());
    Cabecera cabecera=new Cabecera();
    Cliente cliente=new Cliente();
    ValidacionCedula validarced=new ValidacionCedula();
    
    String tipoMensaje="RQ";
    String originador="RESE";
    String idMensaje="REGCLIENTE";
    
    public ClienteReservaGrafico() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtApellidos = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtIdentificacion = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        txtCorreoElectronico = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(376, 490));
        setMinimumSize(new java.awt.Dimension(376, 490));
        setPreferredSize(new java.awt.Dimension(380, 490));
        setSize(new java.awt.Dimension(390, 490));
        getContentPane().setLayout(null);

        txtApellidos.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });
        getContentPane().add(txtApellidos);
        txtApellidos.setBounds(50, 250, 150, 21);

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Apellido");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(50, 230, 60, 15);
        jLabel11.getAccessibleContext().setAccessibleName("Apellido");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo de Identificación");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 70, 130, 15);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Identificación");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 130, 90, 10);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 180, 60, 10);

        jComboBox1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAS", "CED", "RUC" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(50, 100, 150, 20);

        txtIdentificacion.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        getContentPane().add(txtIdentificacion);
        txtIdentificacion.setBounds(50, 150, 152, 21);

        txtNombres.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombres);
        txtNombres.setBounds(50, 200, 150, 21);

        btnEnviar.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        btnEnviar.setText("ENVIAR");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnviar);
        btnEnviar.setBounds(250, 320, 90, 23);

        txtCorreoElectronico.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        getContentPane().add(txtCorreoElectronico);
        txtCorreoElectronico.setBounds(50, 450, 152, 21);

        txtTelefono.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        getContentPane().add(txtTelefono);
        txtTelefono.setBounds(50, 400, 150, 21);

        txtDireccion.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        getContentPane().add(txtDireccion);
        txtDireccion.setBounds(50, 350, 150, 21);

        txtPais.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaisActionPerformed(evt);
            }
        });
        getContentPane().add(txtPais);
        txtPais.setBounds(50, 300, 150, 21);

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("País");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 280, 40, 15);

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Dirección");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(50, 330, 70, 15);

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Teléfono");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(50, 380, 60, 14);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Correo Electrónico");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(50, 430, 130, 15);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/espe/distribuidas/cliente/reserva/24135586_1982061298735718_1598734029_n.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 0, 376, 510);

        getAccessibleContext().setAccessibleParent(jLabel6);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
      try{
          
          boolean id=false;  
          String cabeceraMensaje="";
            String server= "192.168.1.109";
            String cuerpo="";
            String mensaje="";
            Integer port=2000;
            SocketClienteReserva client = new SocketClienteReserva(server, port);
            client.connect();
            cliente.getDatos(jComboBox1, txtIdentificacion, txtNombres, 
                    txtApellidos, txtPais,
                    txtDireccion, txtTelefono, txtCorreoElectronico);
            //validarced.validadorDeCedula(numeroIdentificacion);
            cuerpo=cliente.getCuerpo();
            cabeceraMensaje=cabecera.getCabecera(tipoMensaje, originador, 
                    idMensaje, cuerpo,cuerpo);
            mensaje=cabecera.getMensaje(cabeceraMensaje, cuerpo);
            System.out.println(mensaje);
            client.send(mensaje);
            
            String messageFromServer=client.read();
            //client.read();
            
      }catch(IOException e)
        {
            LOG.log(Level.SEVERE,"Ocurrio un error",e);
        }
       

    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped

    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaisActionPerformed

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosKeyTyped

    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteReservaGrafico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreoElectronico;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
