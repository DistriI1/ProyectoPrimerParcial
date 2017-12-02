/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;

import java.math.BigDecimal;
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
public class Crucero {
    
    private Integer codigo;
    private String registro;
    private String nombre;
    private String tipo;
    private BigDecimal capacidad;

    public Crucero() {
    }

    public Crucero(Integer codigo, String registro, String nombre, String tipo, BigDecimal capacidad) {
        this.codigo = codigo;
        this.registro = registro;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
    }
    
    private List<String[]> listado = new ArrayList<>();

    public List<String[]> getListado() {
        return listado;
    }

    public void setListado(List<String[]> listado) {
        this.listado = listado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(BigDecimal capacidad) {
        this.capacidad = capacidad;
    }    
    
     public boolean solicitarTour() {
       Conexion cn = new Conexion();
       boolean bandera = true;
        try {

            cn.conectar();
            CallableStatement cst = cn.prepareCall("{call camarotesTour (?,?)}");
            cst.setInt(1, codigo);
            //Creo un cursor
            cst.registerOutParameter (2, OracleTypes.CURSOR);
            cst.execute ();
            ResultSet rset = (ResultSet)cst.getObject (2);
            // Dump the cursor
            while (rset.next ()){
                String[] tipo  = new String[5];
                tipo[0] = rset.getString("COD_CRUCERO");
                tipo[1] = rset.getString("COD_TIPO_TOUR");
                tipo[2] = rset.getString("CAPACIDAD");
                tipo[3] = rset.getString("NUMERO");
                tipo[4] = rset.getString("UBICACION");
                listado.add(tipo);
            }
            
            // Cierro todos los recursos
            rset.close();
            cst.close();
            cst.close();
            
        } catch (SQLException ex) {
            bandera = false;
            System.out.println("Error: " + ex.getMessage());
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
