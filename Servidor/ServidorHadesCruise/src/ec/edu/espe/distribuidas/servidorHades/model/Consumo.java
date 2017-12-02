/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.servidorHades.model;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
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
    private String fecha;
    private String referencia;

    public Consumo() {
    }

    public Consumo(Integer codigoItem, String codigoReserva, Integer cantidad, BigDecimal valor, String fecha, String referencia) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean registroConsumo(int codigoTour, int numeroCamarote) {
        Conexion cn = new Conexion();
        boolean bandera = true;
        try {
            // Carga el driver de oracle
            cn.conectar();

            // Llamada al procedimiento almacenado
            CallableStatement cst = cn.prepareCall("{call registroConsumo (?,?,?,?,?,?,?)}");

            cst.setInt(1, codigoTour);
            cst.setInt(2, numeroCamarote);
            cst.setInt(3, codigoItem);
            cst.setInt(4, cantidad);
            cst.setString(5, referencia);
            cst.setString(6, fecha);
            cst.setString(7, valor.toString());

            System.out.println("Valores seteados");

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
}
