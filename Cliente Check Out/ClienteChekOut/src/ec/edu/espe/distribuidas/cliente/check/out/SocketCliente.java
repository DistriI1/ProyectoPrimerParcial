/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.check.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author toshiba
 */
public class SocketCliente {

   private static final Logger LOG = Logger.getLogger(SocketCliente.class.getName());
    
    
    
    private String server; 
    private Integer port;
    private Socket client;
    private BufferedWriter oos =null;
    private BufferedReader ois =null;

    public SocketCliente(String server, Integer port) {
        this.server = server;
        this.port = port;
    }

    public SocketCliente() {
    }
    
    
    public void  connect() throws IOException{
        this.client=new Socket(this.server, this.port);
        oos=new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream()));
        ois = new BufferedReader(new InputStreamReader(this.client.getInputStream()));

    }
    
    public String read() throws IOException
    {
        return this.ois.readLine();
    }
    
    public void send(String message) throws IOException
    {
        this.oos.append(message);
        this.oos.newLine();
        this.oos.flush();
    }
}
