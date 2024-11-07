package com.example.tpaidiseno.Entidades;


import java.sql.Date;

public class Siguiendo {
    private Date fechaInicio;
    private Date fechaFin;
    private Bodega bodega;
    private Enofilo amigo;

    /**
     * Constructor for Siguiendo class
     *
     * @param fechaInicio Start date of following
     * @param fechaFin End date of following
     * @param bodega The bodega being followed
     */
    public Siguiendo(Date fechaInicio, Date fechaFin, Bodega bodega) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.bodega = bodega;
    }

    public Siguiendo() {

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

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Enofilo getAmigo() {
        return amigo;
    }

    public void setAmigo(Enofilo amigo) {
        this.amigo = amigo;
    }

    @Override
    public String toString() {
        return "Siguiendo{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", bodega=" + bodega +
                '}';
    }

    /**
     * Step 7 of Use Case
     * Checks if this following relationship is with the specified bodega
     *
     * @param bod The bodega to check
     * @return true if the following is for this bodega, false otherwise
     */
    public boolean sosDeBodega(Bodega bod) {
        if (bod == bodega) return true;
        else return false;
    }


}