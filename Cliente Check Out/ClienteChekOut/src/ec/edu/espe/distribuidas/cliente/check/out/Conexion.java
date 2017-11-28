/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.check.out;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAVID
 */
public class Conexion {
    public void conectar(){
          try{
            String server= "192.168.1.114";
            Integer port=2000;
            SocketCheckOut client = new SocketCheckOut(server, port);
            client.connect();
        }
        
        catch(IOException e)
        {
            LOG.log(Level.SEVERE,"Ocurrio un error",e);
        }
    }
    private static final Logger LOG = Logger.getLogger(Start.class.getName());
    
}
