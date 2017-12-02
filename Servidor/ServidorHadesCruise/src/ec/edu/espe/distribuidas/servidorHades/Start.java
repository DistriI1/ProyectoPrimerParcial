/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades;

import ec.edu.espe.distribuidas.servidorHades.model.Camarote;
import ec.edu.espe.distribuidas.servidorHades.model.Tour;
import ec.edu.espe.distribuidas.servidorHades.model.Cliente;
import ec.edu.espe.distribuidas.servidorHades.model.Consumo;
import ec.edu.espe.distribuidas.servidorHades.model.Crucero;
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
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
            String fechaInicial = "";
            Fecha fecha = new Fecha();
            String cabeza = "";
            String cuerpo = "";
            //String messageToClient = "SERVIDOR";
            //out.println(messageToClient);
            Cliente cliente;
            Tour tour;
            Reserva reserva;
            TuristaReserva turistaReserva;
            TipoAlimentacion tipoAlimentacion;
            TipoTour tipoTour;
            Consumo consumo;
            Crucero crucero;
            Camarote camarote;
            String codigoReserva = "";

            while (true) {
                String messageFromClient = in.readLine();
                Start.LOG.info(messageFromClient);

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
                            cliente.setTipoIdentificacion(cuerpoMensaje[0].substring(66));
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
                        cliente.setIdentificacion(messageFromClient.substring(66));
                        if (cliente.buscarCliente()) {

                            cuerpo += "OKK";
                            cuerpo += cliente.getTipoIdentificacion() + "&" + cliente.getIdentificacion() + "&"
                                    + cliente.getNombre() + "&" + cliente.getApellido() + "&" + cliente.getPais() + "&"
                                    + cliente.getDireccion() + "&" + cliente.getTelefono() + "&" + cliente.getCorreoElectronico();
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
                        if (tipoTour.solicitarTipos()) {
                            List<String[]> listado = new ArrayList<>();
                            listado = tipoTour.getListado();

                            cuerpo += "OKK";
                            for (int i = 0; i < listado.size(); i++) {
                                cuerpo += Arrays.toString(listado.get(i)).replace(", ", "&");
                                cuerpo += "|";
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
                        if (tour.solicitarTour()) {
                            List<String[]> listado = new ArrayList<>();
                            listado = tour.getListado();
                            cuerpo += "OKK";
                            for (int i = 0; i < listado.size(); i++) {
                                cuerpo += Arrays.toString(listado.get(i)).replace(", ", "&");
                                cuerpo += "|";
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

                    case "LISTOURCAM":

                        cuerpoMensaje = messageFromClient.split("&");
                        tour = new Tour();
                        tour.setCodigo(Integer.parseInt(cuerpoMensaje[1]));
                        if (tour.solicitarCamarote()) {
                            List<String[]> listado = new ArrayList<>();
                            listado = tour.getListado();
                            cuerpo += "OKK";
                            for (int i = 0; i < listado.size(); i++) {
                                cuerpo += Arrays.toString(listado.get(i)).replace(", ", "&");
                                cuerpo += "|";
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

                    case "LISTIPALIM":

                        tipoAlimentacion = new TipoAlimentacion();
                        if (tipoAlimentacion.solicitarTiposAlimentacion()) {
                            List<String[]> listado = new ArrayList<>();
                            listado = tipoAlimentacion.getListado();
                            cuerpo += "OKK";
                            for (int i = 0; i < listado.size(); i++) {
                                cuerpo += Arrays.toString(listado.get(i)).replace(", ", "&");
                                cuerpo += "|";
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

                    case "REGRESERVA":

                        reserva = new Reserva();
                        cuerpoMensaje = messageFromClient.split("&");
                        reserva.setIdentificacion(cuerpoMensaje[0].substring(66));
                        reserva.setCodigoTour(Integer.parseInt(cuerpoMensaje[1]));
                        reserva.setCodigoCrucero(Integer.parseInt(cuerpoMensaje[2]));
                        reserva.setCodigoCamarote(Integer.parseInt(cuerpoMensaje[3]));
                        reserva.setCodigoTipoAlimentacion(cuerpoMensaje[4]);
                        reserva.setValorFinal(cuerpoMensaje[5]);
                        reserva.setEstado(cuerpoMensaje[6]);

                        if (reserva.registrarCliente()) {
                            cuerpo += "OKK";
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

                    case "REGTURISTA":

                        turistaReserva = new TuristaReserva();
                        fechaInicial = "";
                        cuerpoMensaje = messageFromClient.split("&");
                        turistaReserva.setCodigoreserva(cuerpoMensaje[0].substring(66));
                        turistaReserva.setTipoIdentificacion(cuerpoMensaje[1]);
                        turistaReserva.setIdentificacion(cuerpoMensaje[2]);
                        turistaReserva.setNombre(cuerpoMensaje[3]);
                        fechaInicial += (cuerpoMensaje[4].substring(6, 8)) + "/";
                        fechaInicial += (cuerpoMensaje[4].substring(4, 6)) + "/";
                        fechaInicial += (cuerpoMensaje[4].substring(0, 4));
                        turistaReserva.setFechaNacimiento(fechaInicial);

                        if (turistaReserva.registrarTurista()) {
                            cuerpo += "OKK";
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

                    case "LISTTURRES":

                        reserva = new Reserva();
                        reserva.setCodigo(messageFromClient.substring(66));
                        codigoReserva = reserva.getCodigo();

                        if (reserva.listadoTuristas()) {
                            List<String[]> listado = new ArrayList<>();
                            listado = reserva.getListado();
                            cuerpo += "OKK";
                            cuerpo += reserva.getCodigoTipoTour();
                            for (int i = 0; i < listado.size(); i++) {
                                cuerpo += Arrays.toString(listado.get(i)).replace(", ", "&");
                                cuerpo += "|";
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

                    case "REGPESOMAL":

                        turistaReserva = new TuristaReserva();
                        cuerpoMensaje = messageFromClient.split("&");
                        turistaReserva.setCodigoreserva(codigoReserva);
                        turistaReserva.setIdentificacion(cuerpoMensaje[0].substring(66));
                        turistaReserva.setPesoMaleta(cuerpoMensaje[1]);

                        if (turistaReserva.registrarMaleta()) {
                            cuerpo += "OKK";
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

                    case "REGCONSTUR":

                        reserva = new Reserva();
                        consumo = new Consumo();
                        camarote = new Camarote();
                        fechaInicial = "";
                        cuerpoMensaje = messageFromClient.split("&");
                        reserva.setCodigoTour(Integer.parseInt(cuerpoMensaje[0].substring(66)));
                        camarote.setNumero(Integer.parseInt(cuerpoMensaje[1]));
                        consumo.setCodigoItem(Integer.parseInt(cuerpoMensaje[2]));
                        consumo.setCantidad(Integer.parseInt(cuerpoMensaje[3]));
                        consumo.setReferencia(cuerpoMensaje[4]);
                        fechaInicial += (cuerpoMensaje[5].substring(6, 8)) + "/";
                        fechaInicial += (cuerpoMensaje[5].substring(4, 6)) + "/";
                        fechaInicial += (cuerpoMensaje[5].substring(0, 4));
                        consumo.setFecha(fechaInicial);
                        consumo.setValor(new BigDecimal(cuerpoMensaje[6]));

                        if (consumo.registroConsumo(reserva.getCodigoTour(), camarote.getNumero())) {
                            cuerpo += "OKK";
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

                    case "FACTCONCLI":

                        reserva = new Reserva();
                        reserva.setCodigo(messageFromClient.substring(66));

                        if (reserva.factura()) {
                            cuerpo += "OKK";
                            cuerpo += reserva.getTotalConsumos() + "&" + reserva.getRecargo();
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

                    case "REPVENTOUR":

                        reserva = new Reserva();
                        reserva.setCodigoTour(Integer.parseInt(messageFromClient.substring(66)));
                        if (reserva.reporteConsolidado()) {
                            cuerpo += "OKK";
                            cuerpo += reserva.getTotalConsumos() + "&" + reserva.getNombreTour();
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
