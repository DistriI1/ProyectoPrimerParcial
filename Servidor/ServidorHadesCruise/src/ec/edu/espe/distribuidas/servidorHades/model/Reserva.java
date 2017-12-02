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
import java.util.Date;
import java.util.List;
import oracle.jdbc.OracleTypes;

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
    private String valorFinal;
    private Date fechaEmision;
    private String estado;

    private List<String[]> listado = new ArrayList<>();

    public List<String[]> getListado() {
        return listado;
    }

    public void setListado(List<String[]> listado) {
        this.listado = listado;
    }
    
    public Reserva() {
    }

    public Reserva(String codigo, String identificacion, String tipoIdentificacion, Integer codigoTour, String codigoTipoTour, Integer codigoCrucero, Integer codigoCamarote, String codigoTipoCamarote, String codigoTipoAlimentacion, String valorFinal, Date fechaEmision, String estado) {
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

    public String getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(String valorFinal) {
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

    public boolean registrarCliente() {

        Conexion cn = new Conexion();
        boolean bandera = true;

        try {
            // Carga el driver de oracle
            cn.conectar();

            // Llamada al procedimiento almacenado
            CallableStatement cst = cn.prepareCall("{call ingresoCliente (?,?,?,?,?,?,?,?)}");

            //Seteo los valores
            cst.setString(1, codigo);
            cst.setString(2, identificacion);
            cst.setInt(3, codigoCrucero);
            cst.setInt(4, codigoCamarote);
            cst.setString(5, codigoTipoAlimentacion);
            cst.setString(6, valorFinal);
            cst.setString(7, estado);
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
    
        public boolean listadoTuristas() {
        Conexion cn = new Conexion();
        boolean bandera = true ;

        try {

            cn.conectar();
            CallableStatement cst = cn.prepareCall("{call listadoTuristas (?,?,?)}");

            //Creo un cursor
            cst.setString(1, codigo);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.execute();
            
            ResultSet rset = (ResultSet) cst.getObject(2);

            // Dump the cursor
            while (rset.next()) {
                String[] tipo = new String[1];
                tipo[0] = rset.getString("IDENTIFICACION");
                listado.add(tipo);
            }
            codigoTipoTour = cst.getString(3);

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
