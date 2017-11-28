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
public class TipoTour {

    private String cogido;
    private String nombre;
    private String descripcion;
    private Integer duracion;

    public TipoTour() {
    }

    public TipoTour(String cogido, String nombre, String descripcion, Integer duracion) {
        this.cogido = cogido;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public String getCogido() {
        return cogido;
    }

    public void setCogido(String cogido) {
        this.cogido = cogido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
}
