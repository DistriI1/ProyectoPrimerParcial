package ec.edu.espe.distribuidas.cliente.check.out;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DAVID
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conn = new Conexion();
        FrameCheckOut frm = new FrameCheckOut();
        conn.conectar();
        frm.setVisible(true);
    }
    }
    

