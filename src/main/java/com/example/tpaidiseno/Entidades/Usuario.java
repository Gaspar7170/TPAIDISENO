package com.example.tpaidiseno.Entidades;


public class Usuario {
    private String nombre;
    private String contrasenia;
    private boolean premium;

    // Default constructor
    public Usuario() { }

    /**
     * Parameterized constructor for Usuario
     *
     * @param nombre Username
     * @param contrasenia Password
     * @param premium Premium status
     */
    public Usuario(String nombre, String contrasenia, boolean premium) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.premium = premium;
    }

    /**
     * Step 7 of Use Case
     * Gets the username
     *
     * @return The username
     */
    public String getNombre() {
        return nombre;
    }
}