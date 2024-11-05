package com.example.tpaidiseno.Entidades;


import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.util.List;

public class Vino {
    // Es aniada es por no utilizar ñ en un atributo
    private Bodega bodega;
    private int aniada;
    private LocalDateTime fechaActualizacion;
    // Este tambien puede ser byte[]
    private Image imagenEtiqueta;
    private String nombre;
    private String notaDeCataBodega;
    private double precioARS;
    private Maridaje maridaje;
    private List<Varietal> variedades;

    public Vino() {
    }

    public Vino(Bodega bodega, int aniada, LocalDateTime fechaActualizacion,
                Image imagenEtiqueta, String nombre, String notaDeCataBodega,
                double precioARS, Maridaje maridaje, List<Varietal> variedades) {
        this.bodega = bodega;
        this.aniada = aniada;
        this.fechaActualizacion = fechaActualizacion;
        this.imagenEtiqueta = imagenEtiqueta;
        this.nombre = nombre;
        this.notaDeCataBodega = notaDeCataBodega;
        this.precioARS = precioARS;
        this.maridaje = maridaje;
        this.variedades = variedades;
    }

    // region Paso 6 del Caso de Uso
    public boolean sosVinoParaActualizar() {
        return LocalDateTime.now().isAfter(fechaActualizacion);
    }

    public String getNombre() {
        return nombre;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public double getPrecio() {
        return precioARS;
    }

    public void setPrecio(double precio) {
        this.precioARS = precio;
    }

    public void setNotaCata(String nota) {
        this.notaDeCataBodega = nota;
    }

    public String getNota() {
        return notaDeCataBodega;
    }

    public void setImagenEtiqueta(Image imag) {
        this.imagenEtiqueta = imag;
    }

    public Image getImagenEtiqueta() {
        return imagenEtiqueta;
    }

    public void setFechaActualizacion(LocalDateTime fecha) {
        this.fechaActualizacion = fecha;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    /*------------------------- PARA CREAR NUEVO VINO -----------------------*/
    public Maridaje getMaridaje() {
        return maridaje;
    }

    public List<Varietal> getVariedades() {
        return variedades;
    }

    public int getAnianada() {
        return aniada;
    }
    // endregion
}