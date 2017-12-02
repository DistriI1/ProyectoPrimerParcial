/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.reserva;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author toshiba
 */
public class Fecha {
    public String obtenerFecha() {
           //Instanciamos el objeto Calendar
        //en fecha obtenemos la fecha y hora del sistema
        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del aÃ±o, mes, dÃ­a,
        //hora, minuto y segundo del sistema
        //usando el mÃ©todo get y el parÃ¡metro correspondiente
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        //mes = mes;
        
        String diaS;
        String mesS;
        String horaS;
        String minutoS;
        String segundoS;

        
        if(dia < 10){
            diaS = "0"+dia;
        }
        else{
            diaS = ""+dia;
        }
        
        if(mes < 10){
            mesS = "0"+mes;
        }
        else{
            mesS = ""+mes;
        }
        
        if(hora < 10){
            horaS = "0"+hora;
        }
        else{
            horaS = ""+hora;
        }
         
        
        if(minuto < 10){
            minutoS = "0"+minuto;
        }
        else{
            minutoS = ""+minuto;
        }
         
        if(segundo < 10){
            segundoS = "0"+segundo;
        }
        else{
            segundoS = ""+segundo;
        }

        String fechaFinal = ""+anio+(mes+1)+diaS+horaS+minutoS+segundoS;
      
        return fechaFinal;
    }

    public Fecha() {
    }
    

    
}
