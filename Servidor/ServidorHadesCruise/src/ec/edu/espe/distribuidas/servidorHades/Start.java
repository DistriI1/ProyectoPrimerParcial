/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades;

import ec.edu.espe.distribuidas.servidorHades.model.Tour;
import ec.edu.espe.distribuidas.servidorHades.model.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joel
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static final Logger LOG = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) {

        LOG.info("SERVIDOR HADES");

        try {
            ServerSocket server = new ServerSocket(2001);
            LOG.info("Servidor esperando conexiones por el puerto 2001");

            while (true) {
                Socket socket = server.accept();
                LOG.info("Conexion abierta");
                Client client = new Client(socket);
                client.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Client extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintStream out;

    Client(Socket socket) {
        try {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {

        }
    }

    @Override
    public void run() {

        try {
            String respuesta = "";
            //String messageToClient = "SERVIDOR";
            //out.println(messageToClient);

            while (true) {
                String messageFromClient = in.readLine();
                Start.LOG.info(messageFromClient);
                Cliente cliente;
                Tour tour;

                if (messageFromClient.contains("salir")) {
                    socket.close();
                    Start.LOG.info("Conexión Cerrada");
                    break;
                }

                String idMensaje = "";
                String[] cuerpoMensaje;

                idMensaje = messageFromClient.substring(23, 33);

                switch (idMensaje) {
                    case "REGCLIENTE":

                        try {
                            cuerpoMensaje = messageFromClient.split("&");
                            cliente = new Cliente();
                            cliente.setTipoIdentificacion(cuerpoMensaje[0].substring(65, 68));
                            cliente.setIdentificacion(cuerpoMensaje[1]);
                            cliente.setNombre(cuerpoMensaje[2]);
                            cliente.setPais(cuerpoMensaje[3]);
                            cliente.setDireccion(cuerpoMensaje[4]);
                            cliente.setTelefono(cuerpoMensaje[5]);
                            cliente.setCorreoElectronico(cuerpoMensaje[6]);
                            out.println(cliente.getTipoIdentificacion());
                        } catch (Exception e) {
                            Start.LOG.info("error" + e.getMessage());
                        }
                        break;

                    case "BUSCLIENTE":

                        cliente = new Cliente();
                        cliente.setIdentificacion(messageFromClient.substring(65));
                        break;

                    case "LISTIPTOUR":

                        String lttour = "LTTOUR";
                        break;

                    case "LISTOURSEC":

                        String ltour = "LTOUR";
                        cuerpoMensaje = messageFromClient.split("&");
                        tour = new Tour();
                        tour.setCodigoTipoTour(cuerpoMensaje[1]);
                        break;

                    case "LISTOURCAM":

                        String lcam = "LCAM";
                        cuerpoMensaje = messageFromClient.split("&");
                        tour = new Tour();
                        tour.setCodigo(Integer.parseInt(cuerpoMensaje[1]));
                        break;

                    case "LISTIPALIM":

                        String ltali = "LTALI";
                        break;

                    case "REGRESERVA":

//                        String ltali = "LTALI";
//                        break;
                }

//                out.println(respuesta);
//                out.flush();
//                respuesta = "";
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
