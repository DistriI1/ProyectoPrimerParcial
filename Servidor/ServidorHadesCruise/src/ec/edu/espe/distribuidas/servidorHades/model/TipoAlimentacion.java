/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;

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
public class TipoAlimentacion {
    
    private String codigo;
    private String descripcion;
    private List<String[]> listado = new ArrayList<>();

    public List<String[]> getListado() {
        return listado;
    }

    public void setListado(List<String[]> listado) {
        this.listado = listado;
    }
    
    
    public TipoAlimentacion() {
    }

    public TipoAlimentacion(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean solicitarTiposAlimentacion() {
        Conexion cn = new Conexion();
        boolean bandera = true ;

        try {

            cn.conectar();
            CallableStatement cst = cn.prepareCall("{call listadoAlimentacion (?)}");

            //Creo un cursor
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();
            ResultSet rset = (ResultSet) cst.getObject(1);

            // Dump the cursor
            while (rset.next()) {
                String[] tipo = new String[4];
                tipo[0] = rset.getString("COD_TIPO_ALIMENTACION");
                tipo[1] = rset.getString("DESCRIPCION");
                listado.add(tipo);
            }

            // Cierro todos los recursos
            rset.close();
            cst.close();
            cst.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            bandera=false;
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        return bandera;
    }
}
