/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author joel
 */
public class TuristaReserva {
    
    private Integer codigo;
    private String codigoreserva;
    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private Date fechaNacimiento;
    private BigDecimal pesoMaleta;

    public TuristaReserva() {
    }

    public TuristaReserva(Integer codigo, String codigoreserva, String tipoIdentificacion, String identificacion, String nombre, Date fechaNacimiento, BigDecimal pesoMaleta) {
        this.codigo = codigo;
        this.codigoreserva = codigoreserva;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.pesoMaleta = pesoMaleta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoreserva() {
        return codigoreserva;
    }

    public void setCodigoreserva(String codigoreserva) {
        this.codigoreserva = codigoreserva;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public BigDecimal getPesoMaleta() {
        return pesoMaleta;
    }

    public void setPesoMaleta(BigDecimal pesoMaleta) {
        this.pesoMaleta = pesoMaleta;
    }    
}
