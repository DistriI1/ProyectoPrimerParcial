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
            if(i<=65&&i>1&&i>5&&i>19&&i>29&&i>33){
                hashR += arrayChar[i];
            }
            if(i>65&&i<=68){
                respuesta += arrayChar[i];
                //band=i;
            }
            if(i>68 && arrayChar[i]!='&'){
                //totalConsumos = i;
                respuesta1 += arrayChar[i];
                
                
                }
            if(arrayChar[i]=='&'){
                    band=i;
                    break;
            }   
            
        }
        if(arrayChar[band]=='&')
                {
                    for (int k = band+1; k < arrayChar.length; k++) {
                        respuesta2 += arrayChar[k];
                    }
                }
        if(respuesta.equals("OKK")){
            Object [] fila=new Object[3];
            fila[0]=txtCodigoReserva.getText(); 
            fila[1]=respuesta2; 
            fila[2]=respuesta1; 
            modelo.addRow(fila); 
            jTable1.setModel(modelo); 
            txtCodigoReserva.setText("");
        }
        else{
            txtCodigoReserva.setText("");
            JOptionPane.showMessageDialog(null, "CODIGO INCORRECTO, INGRESE NUEVAMENTE");
        }
    }
}
