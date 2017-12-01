/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author joel
 */
public class Conexion {

    Connection cn = null;
    private static final Logger LOG = Logger.getLogger(Conexion.class.getName());

    public Connection conectar() throws SQLException {

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Conecta con la base de datos orcldist con el usuario system y la contraseña password
            cn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.110:1521:orcldist", "c##distribuidas", "distribuidas112358");
        } catch (Exception e) {
            LOG.info("Error en la conexion a la base de datos" + e.getMessage());
        }
        
        return cn;
    }
    
    public CallableStatement prepareCall(String statement)
    {
        CallableStatement cst = null;
        try {
            cst = cn.prepareCall(statement);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cst;
    }
    
    public void close() throws SQLException
    {
        cn.close();
    }

}
