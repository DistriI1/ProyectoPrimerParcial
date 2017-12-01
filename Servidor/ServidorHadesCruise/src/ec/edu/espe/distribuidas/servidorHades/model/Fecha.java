/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;
import java.util.*;
/**
 *
 * @author Daniel
 */
public class Fecha {
    public String obtenerFecha() {
           //Instanciamos el objeto Calendar
        //en fecha obtenemos la fecha y hora del sistema
        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //hora, minuto y segundo del sistema
        //usando el método get y el parámetro correspondiente
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        
  
        mes = mes+1;
        
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
            
        
        String fechaFinal = ""+año+(mes+1)+diaS+horaS+minutoS+segundoS;
      
        return fechaFinal;
    }

    public Fecha() {
    }
    
    
    
}
