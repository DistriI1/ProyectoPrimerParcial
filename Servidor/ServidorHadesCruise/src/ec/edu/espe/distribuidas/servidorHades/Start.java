/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades;

import ec.edu.espe.distribuidas.servidorHades.model.Tour;
import ec.edu.espe.distribuidas.servidorHades.model.Cliente;
import ec.edu.espe.distribuidas.servidorHades.model.Consumo;
import ec.edu.espe.distribuidas.servidorHades.model.Fecha;
import ec.edu.espe.distribuidas.servidorHades.model.Reserva;
import ec.edu.espe.distribuidas.servidorHades.model.TipoAlimentacion;
import ec.edu.espe.distribuidas.servidorHades.model.TipoTour;
import ec.edu.espe.distribuidas.servidorHades.model.TuristaReserva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
            Fecha fecha = new Fecha();
            String cabeza = "";
            String cuerpo = "";
            //String messageToClient = "SERVIDOR";
            //out.println(messageToClient);

            while (true) {
                String messageFromClient = in.readLine();
                Start.LOG.info(messageFromClient);
                Cliente cliente;
                Tour tour;
                Reserva reserva;
                TuristaReserva turistaReserva;
                TipoAlimentacion tipoAlimentacion;
                TipoTour tipoTour;
                Consumo consumo;
                Date fechaTemp;

                if (messageFromClient.contains("salir")) {
                    socket.close();
                    Start.LOG.info("Conexión Cerrada");
                    break;
                }

                String idMensaje = "";
                String[] cuerpoMensaje;

                idMensaje = messageFromClient.substring(20, 30);

                switch (idMensaje) {
                    case "REGCLIENTE":

                        try {
                            cuerpoMensaje = messageFromClient.split("&");
                            cliente = new Cliente();
                            cliente.setTipoIdentificacion(cuerpoMensaje[0].substring(64));
                            cliente.setIdentificacion(cuerpoMensaje[1]);
                            cliente.setNombre(cuerpoMensaje[2]);
                            cliente.setApellido(cuerpoMensaje[3]);
                            cliente.setPais(cuerpoMensaje[4]);
                            cliente.setDireccion(cuerpoMensaje[5]);
                            cliente.setTelefono(cuerpoMensaje[6]);
                            cliente.setCorreoElectronico(cuerpoMensaje[7]);

                            if (cliente.registrarCliente()) {
                                cuerpo += "OKK";
                                cabeza += "RPSERV";
                                cabeza += fecha.obtenerFecha() + idMensaje + longitudCuerpo(cuerpo.length()) + md5(cuerpo);
                                out.println(cabeza + cuerpo);
                                out.flush();
                                respuesta = "";
                                cuerpo = "";
                            } else {
                                cuerpo += "BAD";
                                cabeza += "RPSERV";
                                cabeza += fecha.obtenerFecha() + idMensaje + longitudCuerpo(cuerpo.length()) + md5(cuerpo);
                                out.println(cabeza + cuerpo);
                                out.flush();
                                cabeza = "";
                                cuerpo = "";
                            }

                        } catch (Exception e) {
                            Start.LOG.info("error" + e.getMessage());
                        }
                        break;

                    case "BUSCLIENTE":

                        cliente = new Cliente();
                        cliente.setIdentificacion(messageFromClient.substring(64));
                        if (cliente.buscarCliente()) {

                            cuerpo += "OKK";
                            cuerpo += cliente.getTipoIdentificacion() + "&" + cliente.getIdentificacion() + "&"
                                    + cliente.getNombre() + "&" + cliente.getPais() + "&" + cliente.getDireccion() + "&"
                                    + cliente.getTelefono() + "&" + cliente.getCorreoElectronico();
                            cabeza += "RPSERV";
                            cabeza += fecha.obtenerFecha() + idMensaje + longitudCuerpo(cuerpo.length()) + md5(cuerpo);
                            out.println(cabeza + cuerpo);
                            out.flush();
                            cabeza = "";
                            cuerpo = "";
                        } else {

                            cuerpo += "BAD";
                            cabeza += "RPSERV";
                            cabeza += fecha.obtenerFecha() + idMensaje + longitudCuerpo(cuerpo.length()) + md5(cuerpo);
                            out.println(cabeza + cuerpo);
                            out.flush();
                            cabeza = "";
                            cuerpo = "";
                        }

                        break;

                    case "LISTIPTOUR":

                        tipoTour = new TipoTour();
                        if (tipoTour.solicitarTipos()) 
                        {
                            List<String[]> listado = new ArrayList<>();
                            listado = tipoTour.getListado();
                            
                            cuerpo += "OKK";
                            for(int i =0; i<listado.size();i++)
                            {
                                cuerpo+=Arrays.toString(listado.get(i)).replace(", ", "&");                                
                                cuerpo+="|";
                            }
                            cuerpo = cuerpo.replace("[", "");
                            cuerpo = cuerpo.replace("]", "");
                            cabeza += "RPSERV";
                            cabeza += fecha.obtenerFecha() + idMensaje + longitudCuerpo(cuerpo.length()) + md5(cuerpo);
                            out.println(cabeza + cuerpo);
                            out.flush();
                            cabeza = "";
                            cuerpo = "";
                            
                        } else {
                            cuerpo += "BAD";
                            cabeza += "RPSERV";
                            cabeza += fecha.obtenerFecha() + idMensaje + longitudCuerpo(cuerpo.length()) + md5(cuerpo);
                            out.println(cabeza + cuerpo);
                            out.flush();
                            cabeza = "";
                            cuerpo = "";
                        }
                        break;

                    case "LISTOURSEC":

                        cuerpoMensaje = messageFromClient.split("&");
                        tour = new Tour();
                        tour.setCodigoTipoTour(cuerpoMensaje[1]);
                        if(tour.solicitarTour())
                        {
                            List<String[]> listado = new ArrayList<>();
                            listado=tour.getListado();
                            cuerpo += "OKK";
                            for(int i =0; i<listado.size();i++)
                            {
                                cuerpo+=Arrays.toString(listado.get(i)).replace(", ", "&");                                
                                cuerpo+="|";
                            }
                            cuerpo = cuerpo.replace("[", "");
                            cuerpo = cuerpo.replace("]", "");
                            cabeza += "RPSERV";
                            cabeza += fecha.obtenerFecha() + idMensaje + longitudCuerpo(cuerpo.length()) + md5(cuerpo);
                            out.println(cabeza + cuerpo);
                            out.flush();
                            cabeza = "";
                            cuerpo = "";
                        }
                        else
                        {
                            cuerpo += "BAD";
                            cabeza += "RPSERV";
                            cabeza += fecha.obtenerFecha() + idMensaje + longitudCuerpo(cuerpo.length()) + md5(cuerpo);
                            out.println(cabeza + cuerpo);
                            out.flush();
                            cabeza = "";
                            cuerpo = "";
                        }
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

                        reserva = new Reserva();
                        cuerpoMensaje = messageFromClient.split("&");
                        reserva.setIdentificacion(cuerpoMensaje[0].substring(64));
                        reserva.setCodigoTour(Integer.parseInt(cuerpoMensaje[1]));
                        reserva.setCodigoCrucero(Integer.parseInt(cuerpoMensaje[2]));
                        reserva.setCodigoCamarote(Integer.parseInt(cuerpoMensaje[3]));
                        reserva.setCodigoTipoAlimentacion(cuerpoMensaje[4]);
                        reserva.setEstado(cuerpoMensaje[5]);
                        break;

                    case "REGTURISTA":

                        turistaReserva = new TuristaReserva();
                        cuerpoMensaje = messageFromClient.split("&");
                        turistaReserva.setCodigoreserva(cuerpoMensaje[0].substring(64));
                        turistaReserva.setTipoIdentificacion(cuerpoMensaje[1]);
                        turistaReserva.setIdentificacion(cuerpoMensaje[2]);
                        turistaReserva.setNombre(cuerpoMensaje[3]);
                        fechaTemp = new Date();
                        fechaTemp.setYear(Integer.parseInt(cuerpoMensaje[4].substring(0, 3)));
                        fechaTemp.setMonth(Integer.parseInt(cuerpoMensaje[4].substring(4, 5)));
                        fechaTemp.setMonth(Integer.parseInt(cuerpoMensaje[4].substring(6, 7)));
                        turistaReserva.setFechaNacimiento(fechaTemp);
                        break;

                    case "LISTTURRES":

                        turistaReserva = new TuristaReserva();
                        turistaReserva.setCodigoreserva(messageFromClient.substring(64));
                        break;

                    case "REGPESOMAL":

                        turistaReserva = new TuristaReserva();
                        cuerpoMensaje = messageFromClient.split("&");
                        turistaReserva.setIdentificacion(cuerpoMensaje[0].substring(64));
                        turistaReserva.setPesoMaleta(new BigDecimal(cuerpoMensaje[1]));
                        break;

                    case "REGCONSTUR":

                        reserva = new Reserva();
                        consumo = new Consumo();
                        fechaTemp = new Date();
                        cuerpoMensaje = messageFromClient.split("&");
                        reserva.setCodigoTour(Integer.parseInt(cuerpoMensaje[0].substring(64)));
                        reserva.setCodigoCamarote(Integer.parseInt(cuerpoMensaje[1]));
                        consumo.setCodigoItem(Integer.parseInt(cuerpoMensaje[2]));
                        consumo.setCantidad(Integer.parseInt(cuerpoMensaje[3]));
                        consumo.setReferencia(cuerpoMensaje[4]);
                        fechaTemp.setYear(Integer.parseInt(cuerpoMensaje[5].substring(0, 3)));
                        fechaTemp.setMonth(Integer.parseInt(cuerpoMensaje[5].substring(4, 5)));
                        fechaTemp.setYear(Integer.parseInt(cuerpoMensaje[5].substring(6, 7)));
                        consumo.setFecha(fechaTemp);
                        consumo.setValor(new BigDecimal(cuerpoMensaje[6]));
                        break;

                    case "FACTCONCLI":

                        reserva = new Reserva();
                        reserva.setCodigo(messageFromClient.substring(64));
                        break;

                    case "REPVENTOUR":

                        tour = new Tour();
                        tour.setCodigo(Integer.parseInt(messageFromClient.substring(64)));
                        break;
                }

//                out.println(respuesta);
//                out.flush();
//                respuesta = "";
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String longitudCuerpo(int longitud) {
        String respuesta = "";

        String aux = "" + longitud;
        for (int i = 0; i < 4 - aux.length(); i++) {
            respuesta += "0";
        }
        respuesta += longitud;

        return respuesta;
    }

    public String getHash(String cuerpo, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(cuerpo.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* Retorna un hash MD5 a partir de un texto */
    public String md5(String cuerpo) {
        return getHash(cuerpo, "MD5");
    }
}
