package com.example.tpaidiseno.Entidades;


public class Varietal {
    private String descripcion;
    private double porcentajeComposicion;
    private TipoUva tipoUva;

    public Varietal() {
    }

    public Varietal(String descripcion, double porcentajeComposicion, TipoUva tipoUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
    }

    // region Paso 6 del Caso de Uso
    public TipoUva getTipoUva() {
        return tipoUva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTipoUva(TipoUva tipoUva) {
        this.tipoUva = tipoUva;
    }
    // endregion
}