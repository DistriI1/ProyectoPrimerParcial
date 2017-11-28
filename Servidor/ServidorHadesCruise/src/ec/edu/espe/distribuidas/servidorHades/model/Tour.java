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
public class Tour {

    private Integer codigo;
    private String codigoTipoTour;
    private Integer codigoCrucero;
    private String nombre;
    private Integer duracion;
    private Date fechaInicio;
    private Date fechaFin;
    private String puertoEmbarque;
    private String puertoDesembarque;
    private BigDecimal precioBase;
    private Integer porcentajeMenu;

    public Tour() {
    }

    public Tour(Integer codigo, String codigoTipoTour, Integer codigoCrucero, String nombre, Integer duracion, Date fechaInicio, Date fechaFin, String puertoEmbarque, String puertoDesembarque, BigDecimal precioBase, Integer porcentajeMenu) {
        this.codigo = codigo;
        this.codigoTipoTour = codigoTipoTour;
        this.codigoCrucero = codigoCrucero;
        this.nombre = nombre;
        this.duracion = duracion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.puertoEmbarque = puertoEmbarque;
        this.puertoDesembarque = puertoDesembarque;
        this.precioBase = precioBase;
        this.porcentajeMenu = porcentajeMenu;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoTipoTour() {
        return codigoTipoTour;
    }

    public void setCodigoTipoTour(String codigoTipoTour) {
        this.codigoTipoTour = codigoTipoTour;
    }

    public Integer getCodigoCrucero() {
        return codigoCrucero;
    }

    public void setCodigoCrucero(Integer codigoCrucero) {
        this.codigoCrucero = codigoCrucero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getPuertoEmbarque() {
        return puertoEmbarque;
    }

    public void setPuertoEmbarque(String puertoEmbarque) {
        this.puertoEmbarque = puertoEmbarque;
    }

    public String getPuertoDesembarque() {
        return puertoDesembarque;
    }

    public void setPuertoDesembarque(String puertoDesembarque) {
        this.puertoDesembarque = puertoDesembarque;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public Integer getPorcentajeMenu() {
        return porcentajeMenu;
    }

    public void setPorcentajeMenu(Integer porcentajeMenu) {
        this.porcentajeMenu = porcentajeMenu;
    }
}
