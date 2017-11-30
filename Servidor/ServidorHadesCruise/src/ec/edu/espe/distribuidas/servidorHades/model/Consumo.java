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
public class Consumo {
    
    private Integer codigoItem;
    private String codigoReserva;
    private Integer cantidad;
    private BigDecimal valor;
    private Date fecha;
    private String referencia;

    public Consumo() {
    }

    public Consumo(Integer codigoItem, String codigoReserva, Integer cantidad, BigDecimal valor, Date fecha, String referencia) {
        this.codigoItem = codigoItem;
        this.codigoReserva = codigoReserva;
        this.cantidad = cantidad;
        this.valor = valor;
        this.fecha = fecha;
        this.referencia = referencia;
    }

    public Integer getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(Integer codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }    
}
