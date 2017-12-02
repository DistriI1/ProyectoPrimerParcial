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
public class Peticion {
    public Hash hash = new Hash();    
    public String longitudCuerpo(String longitud){
        int numero = 0;
        
       
        String longi="";
        char[] arrayChar = longitud.toCharArray();
		for(int i=0; i<arrayChar.length; i++){
			numero++;
                        
		}
                if(numero<=9){
                    longi = "000" + numero;
                }
                else if(numero>9)
                {
                    longi="00"+numero;
                }
                return longi;
    }
    public String cabecera(String Codigo){
        Fecha fecha = new Fecha();
        String tipoMensaje = "RQ";
        String originador = "CHON";
        String idMensaje = "FACTCONCLI";
        String cuerpoLongitud = longitudCuerpo(Codigo);
        return tipoMensaje+originador+fecha.obtenerFecha()+idMensaje+cuerpoLongitud+hash.hash(Codigo)+Codigo;
    }
}
