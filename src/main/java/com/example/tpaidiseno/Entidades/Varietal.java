package com.example.tpaidiseno.Entidades;


public class Varietal {
    private String nombre;
    private double porcentajeComposicion;
    private TipoUva tipoUva;

    public Varietal() {

    }

    public Varietal(String nombre, double porcentajeComposicion, TipoUva tipoUva) {
        this.nombre = nombre;

        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
    }

    // region Paso 6 del Caso de Uso


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentajeComposicion() {
        return porcentajeComposicion;
    }

    public void setPorcentajeComposicion(double porcentajeComposicion) {
        this.porcentajeComposicion = porcentajeComposicion;
    }

    public TipoUva getTipoUva() {
        return tipoUva;
    }

    public void setTipoUva(TipoUva tipoUva) {
        this.tipoUva = tipoUva;
    }

    // endregion
}