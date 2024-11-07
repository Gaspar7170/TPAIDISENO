package com.example.tpaidiseno.Entidades;


public class Maridaje {
    private int id;
    private String nombre;
    private String descripcion;

    // Default constructor
    public Maridaje() {
    }

    // Parameterized constructor
    public Maridaje(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Maridaje{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}