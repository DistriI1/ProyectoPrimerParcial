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
public class Reserva {
    
    private String codigo;
    private String identificacion;
    private String tipoIdentificacion;
    private Integer codigoTour;
    private String codigoTipoTour;
    private Integer codigoCrucero;
    private Integer codigoCamarote;
    private String codigoTipoCamarote;
    private String codigoTipoAlimentacion;
    private BigDecimal valorFinal;
    private Date fechaEmision;
    private String estado;

    public Reserva() {
    }

    public Reserva(String codigo, String identificacion, String tipoIdentificacion, Integer codigoTour, String codigoTipoTour, Integer codigoCrucero, Integer codigoCamarote, String codigoTipoCamarote, String codigoTipoAlimentacion, BigDecimal valorFinal, Date fechaEmision, String estado) {
        this.codigo = codigo;
        this.identificacion = identificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.codigoTour = codigoTour;
        this.codigoTipoTour = codigoTipoTour;
        this.codigoCrucero = codigoCrucero;
        this.codigoCamarote = codigoCamarote;
        this.codigoTipoCamarote = codigoTipoCamarote;
        this.codigoTipoAlimentacion = codigoTipoAlimentacion;
        this.valorFinal = valorFinal;
        this.fechaEmision = fechaEmision;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getCodigoTour() {
        return codigoTour;
    }

    public void setCodigoTour(Integer codigoTour) {
        this.codigoTour = codigoTour;
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

    public String getCodigoTipoAlimentacion() {
        return codigoTipoAlimentacion;
    }

    public void setCodigoTipoAlimentacion(String codigoTipoAlimentacion) {
        this.codigoTipoAlimentacion = codigoTipoAlimentacion;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    
}
