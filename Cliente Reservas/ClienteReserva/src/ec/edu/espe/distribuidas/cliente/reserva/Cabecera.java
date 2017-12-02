/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.reserva;

import java.util.Calendar;

/**
 *
 * @author toshiba
 */
public class Cabecera {

    Hash hashmd5=new Hash();
    Fecha fecha=new Fecha();
    

    public String getCabecera(String tipoMensaje,String originador,
            String idMensaje, String longitud, String hashCuerpo)
    {
        return tipoMensaje+originador+fecha.obtenerFecha()+
                idMensaje+getLongitud(longitud)+hashmd5.md5(hashCuerpo);
    }
    
    private String getLongitud(String cuerpo)
    {
        int i=cuerpo.length();
        String longitud= Integer.toString(i);
        
        char[] arrayChar = longitud.toCharArray();
        if(arrayChar.length==1)
        {longitud="000"+longitud;
            return longitud;}
        else if (arrayChar.length==2 )
        {longitud="00"+longitud;
            return longitud;}
        else if(arrayChar.length==3)
        {longitud="0"+longitud;
            return longitud;}
        else
            return longitud;
    }
    
    public String getMensaje(String cabecera, String cuerpo)
    {
        return cabecera+cuerpo;
    }
    
}
