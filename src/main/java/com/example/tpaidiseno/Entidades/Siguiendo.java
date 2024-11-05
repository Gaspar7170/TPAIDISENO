package com.example.tpaidiseno.Entidades;


import java.time.LocalDateTime;

public class Siguiendo {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Bodega bodega;

    /**
     * Constructor for Siguiendo class
     *
     * @param fechaInicio Start date of following
     * @param fechaFin End date of following
     * @param bodega The bodega being followed
     */
    public Siguiendo(LocalDateTime fechaInicio, LocalDateTime fechaFin, Bodega bodega) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.bodega = bodega;
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