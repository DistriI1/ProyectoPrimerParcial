package ec.edu.espe.distribuidas.cliente.reserva;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start {
    private static final Logger LOG = Logger.getLogger(Start.class.getName());
    public static void main(String[] args) {
       try{
            String server= "192.168.1.109";
            Integer port=2000;
            SocketClienteReserva client = new SocketClienteReserva(server, port);
            ClienteReservaGrafico clientgraph = new ClienteReservaGrafico();
            clientgraph.show();
            client.connect();
            
           
            String messageFromServer=client.read();
            LOG.info(messageFromServer);

        }      
        catch(IOException e)
        {
            LOG.log(Level.SEVERE,"Ocurrio un error",e);
        }
    }
   
    
}
