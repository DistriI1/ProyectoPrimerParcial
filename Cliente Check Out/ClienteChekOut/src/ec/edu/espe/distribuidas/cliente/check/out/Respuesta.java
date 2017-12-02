/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.check.out;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAVID
 */
public class Respuesta {
        private String tipoMensajeR = "";
        private String originadorR = "";
        private String idMensajeR = "";
        private String fechaHoraR= "";
        private String longitudR = "";
        private String hashR = "";
        private String respuesta = "";
        private String respuesta1 = "";
        private String respuesta2 = "";
        private int totalConsumos = 0;
        private int band=0;
        private int j=0;
        private Integer longitud = 4;
    public Respuesta() {
    }
    
     public void recibirRespuesta(String peticion,JTable jTable1,JTextField txtCodigoReserva){
        DefaultTableModel modelo=(DefaultTableModel) jTable1.getModel(); 
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
            if(i>64&&i<=67){
                respuesta += arrayChar[i];
            }
            if(arrayChar[i]=='&'){
                //totalConsumos = i;
                band++;
                if(band==1){
                    totalConsumos = i;
                for (j = totalConsumos+1; j < arrayChar.length; j++) {
                    if(arrayChar[j]!='&')
                    respuesta1 += arrayChar[j];
                    if(arrayChar[j]=='&')
                    break;
                }
                }
                if(band==2){
                    totalConsumos = i;
                for (j = totalConsumos+1; j < arrayChar.length; j++) {
                    respuesta2 += arrayChar[j];
                }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "TIPO DE MENSAJE " + tipoMensajeR);
        JOptionPane.showMessageDialog(null, "ORIGINADOR " + originadorR);
        JOptionPane.showMessageDialog(null, "FECHA Y HORA " + fechaHoraR);
        JOptionPane.showMessageDialog(null, "ID MENSAJE " + idMensajeR);
        JOptionPane.showMessageDialog(null, "LONGITUD " + longitudR);
        JOptionPane.showMessageDialog(null, "CODIGO HASH " + hashR);
        JOptionPane.showMessageDialog(null, "RESPUESTA " + respuesta);
        JOptionPane.showMessageDialog(null, "TOTAL CONSUMOS " + respuesta1);
        JOptionPane.showMessageDialog(null, "RECARGO DE EQUIPAJE " + respuesta2);
        if(respuesta.equals("OKK")){
            Object [] fila=new Object[3];
            fila[0]=txtCodigoReserva.getText(); 
            fila[1]=respuesta1; 
            fila[2]=respuesta2; 
            modelo.addRow(fila); 
            jTable1.setModel(modelo); 
        }
        else{
            txtCodigoReserva.setText("");
            JOptionPane.showMessageDialog(null, "CODIGO INCORRECTO, INGRESE NUEVAMENTE");
        }
    }
}
