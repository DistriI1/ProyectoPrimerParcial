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

    public boolean registrarTurista() {

        Conexion cn = new Conexion();
        boolean bandera = true;

        try {
            // Carga el driver de oracle
            cn.conectar();

            // Llamada al procedimiento almacenado
            CallableStatement cst = cn.prepareCall("{call ingresoTurista (?,?,?,?,?,?,?,?)}");

            //Seteo los valores
            cst.setInt(1, codigo);
            cst.setString(2, tipoIdentificacion);
            cst.setString(3, identificacion);
            cst.setString(4, nombre);
            cst.setDate(5, (java.sql.Date) fechaNacimiento);
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
