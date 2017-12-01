/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author joel
 */
public class TipoTour {

    private String cogido;
    private String nombre;
    private String descripcion;
    private Integer duracion;
    
    private List<String[]> listado = new ArrayList<>();

    public List<String[]> getListado() {
        return listado;
    }

    public void setListado(List<String[]> listado) {
        this.listado = listado;
    }
    
    public TipoTour() {
    }

    public TipoTour(String cogido, String nombre, String descripcion, Integer duracion) {
        this.cogido = cogido;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public String getCogido() {
        return cogido;
    }

    public void setCogido(String cogido) {
        this.cogido = cogido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public void solicitarTipos() {
       Conexion cn = new Conexion();

        try {

            CallableStatement cst = cn.prepareCall("{call listadoTipoTOUR (?)}");
            
            //Creo un cursor
            cst.registerOutParameter (1, OracleTypes.CURSOR);
            cst.execute ();
            ResultSet rset = (ResultSet)cst.getObject (1);

            // Dump the cursor
            while (rset.next ()){
                String[] tipo  = new String[4];
                tipo[0] = rset.getString("COD_TIPO_TOUR");
                tipo[1] = rset.getString("NOMBRE");
                tipo[2] = rset.getString("DESCRIPCION");
                tipo[3] = rset.getString("DURACION");
                listado.add(tipo);
            }
            
            // Cierro todos los recursos
            rset.close();
            cst.close();
            cst.close();
            
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
