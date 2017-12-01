/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author joel
 */
public class Cliente {

    private String identificacion;
    private String tipoIdentificacion;
    private String nombre;
    private String apellido;
    private String pais;
    private String direccion;
    private String telefono;
    private String correoElectronico;

    public Cliente() {
    }

    public Cliente(String identificacion, String tipoIdentificacion, String nombre, String apellido, String pais, String direccion, String telefono, String correoElectronico) {
        this.identificacion = identificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean registrarCliente() {

        Conexion cn = new Conexion();
        boolean bandera = true;

        try {
            // Carga el driver de oracle
            cn.conectar();

            // Llamada al procedimiento almacenado
            CallableStatement cst = cn.prepareCall("{call ingresoCliente (?,?,?,?,?,?,?,?)}");

            //Seteo los valores
            cst.setString(1, identificacion);
            cst.setString(2, tipoIdentificacion);
            cst.setString(3, nombre);
            cst.setString(4, apellido);
            cst.setString(5, pais);
            cst.setString(6, direccion);
            cst.setString(7, telefono);
            cst.setString(8, correoElectronico);

            System.out.println("Valores seteados");

            System.out.println("" + identificacion + tipoIdentificacion + nombre + apellido + pais + direccion + telefono + correoElectronico);

            cst.execute();

            System.out.println("procedure ejecutado");

            cst.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            bandera = false;
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return bandera;
    }

    public void buscarCliente() {
        Conexion cn = new Conexion();
        try {
            // Carga el driver de oracle
            cn.conectar();
           
            // Llamada al procedimiento almacenado
            CallableStatement cst = cn.prepareCall("{call informacionCliente (?,?,?,?,?,?,?,?)}");
            
                // Parametro 1 del procedimiento almacenado
                cst.setString(1, identificacion);
                
                // Definimos los tipos de los parametros de salida del procedimiento almacenado
                cst.registerOutParameter(2, java.sql.Types.VARCHAR);
                cst.registerOutParameter(3, java.sql.Types.VARCHAR);
                cst.registerOutParameter(4, java.sql.Types.VARCHAR);
                cst.registerOutParameter(5, java.sql.Types.VARCHAR);
                cst.registerOutParameter(6, java.sql.Types.VARCHAR);
                cst.registerOutParameter(7, java.sql.Types.VARCHAR);
                cst.registerOutParameter(8, java.sql.Types.VARCHAR);
                
                // Ejecuta el procedimiento almacenado
                cst.execute();
                
                // Se obtienen la salida del procedimineto almacenado
                tipoIdentificacion = cst.getString(2);
                nombre = cst.getString(3);
                apellido = cst.getString(4);
                pais = cst.getString(5);
                direccion = cst.getString(6);
                telefono = cst.getString(7);
                correoElectronico = cst.getString(8);

            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

}
