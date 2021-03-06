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
}
