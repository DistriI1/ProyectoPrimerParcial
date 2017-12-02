/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.reserva;

import javax.swing.JOptionPane;

/**
 *
 * @author toshiba
 */
public class Respuesta {
    
    public void recibirRespuesta(String peticion){

        String tipoMensajeR = "";
        String originadorR = "";
        String idMensajeR = "";
        String fechaHoraR= "";
        String longitudR = "";
        String hashR = "";
        String respuesta = "";
        String respuesta1 = "";
        String respuesta2 = "";
        int totalConsumos = 0;
        int band=0;
        int j=0;
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
    }

      
    
}
