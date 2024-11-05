package com.example.tpaidiseno.Entidades;


public class TipoUva {
    private String descripcion;
    private String nombre;

    // Default constructor
    public TipoUva() {
    }

    // Parameterized constructor
    public TipoUva(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    /**
     * Step 6 of Use Case
     * Checks if the given TipoUva is equal to this instance
     *
     * @param tipo The TipoUva to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean esTipoUva(TipoUva tipo) {
        if (tipo == this) return true;
        else return false;
    }
}