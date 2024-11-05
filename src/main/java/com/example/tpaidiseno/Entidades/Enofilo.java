package com.example.tpaidiseno.Entidades;

import java.awt.image.BufferedImage;
import java.util.List;

public class Enofilo {
    private String apellido;
    private BufferedImage imagenPerfil;
    private String nombre;
    private List<Siguiendo> seguido; // Can be empty
    private Usuario usuario;

    /**
     * Constructor for Enofilo class
     *
     * @param apellido Last name of the enofilo
     * @param imagenPerfil Profile image
     * @param nombre Name of the enofilo
     * @param seguido List of followed entities
     * @param usuario Associated user
     */
    public Enofilo(String apellido, BufferedImage imagenPerfil, String nombre, List<Siguiendo> seguido, Usuario usuario) {
        this.apellido = apellido;
        this.imagenPerfil = imagenPerfil;
        this.nombre = nombre;
        this.seguido = seguido;
        this.usuario = usuario;
    }

    /**
     * Step 7 of Use Case
     * Checks if this enofilo follows a specific bodega
     *
     * @param bod The bodega to check
     * @return true if the enofilo follows the bodega, false otherwise
     */
    public boolean sigueABodega(Bodega bod) {
        for (Siguiendo seg : seguido) {
            if (seg.sosDeBodega(bod)) return true;
        }
        return false;
    }

    /**
     * Gets the username of the associated user
     *
     * @return The username
     */
    public String getNombreUsuario() {
        return usuario.getNombre();
    }
}