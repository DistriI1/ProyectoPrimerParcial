/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;

/**
 *
 * @author joel
 */
public class Camarote {
    
    private Integer codigoCrucero;
    private Integer codigoCamarote;
    private String codigoTipoCamarote;
    private Integer numero;
    private Integer capacidad;
    private String ubicacion;

    public Camarote() {
    }

    public Camarote(Integer codigoCrucero, Integer codigoCamarote, String codigoTipoCamarote, Integer numero, Integer capacidad, String ubicacion) {
        this.codigoCrucero = codigoCrucero;
        this.codigoCamarote = codigoCamarote;
        this.codigoTipoCamarote = codigoTipoCamarote;
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
    }

    public Integer getCodigoCrucero() {
        return codigoCrucero;
    }

    public void setCodigoCrucero(Integer codigoCrucero) {
        this.codigoCrucero = codigoCrucero;
    }

    public Integer getCodigoCamarote() {
        return codigoCamarote;
    }

    public void setCodigoCamarote(Integer codigoCamarote) {
        this.codigoCamarote = codigoCamarote;
    }

    public String getCodigoTipoCamarote() {
        return codigoTipoCamarote;
    }

    public void setCodigoTipoCamarote(String codigoTipoCamarote) {
        this.codigoTipoCamarote = codigoTipoCamarote;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }    
}
