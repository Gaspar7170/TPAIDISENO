package com.example.tpaidiseno.Entidades;


public class Maridaje {
    public String nombre;
    public String descripcion;

    // Default constructor
    public Maridaje() {
    }

    // Parameterized constructor
    public Maridaje(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Step 6 of Use Case
     * Checks if the given Maridaje is equal to this instance
     *
     * @param mar The Maridaje to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean esMaridaje(Maridaje mar) {
        if (mar == this) return true;
        else return false;
    }
}