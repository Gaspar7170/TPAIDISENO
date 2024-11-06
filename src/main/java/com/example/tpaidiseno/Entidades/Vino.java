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

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public int getAniada() {
        return aniada;
    }

    public void setAniada(int aniada) {
        this.aniada = aniada;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotaDeCataBodega() {
        return notaDeCataBodega;
    }

    public void setNotaDeCataBodega(String notaDeCataBodega) {
        this.notaDeCataBodega = notaDeCataBodega;
    }

    public double getPrecioARS() {
        return precioARS;
    }

    public void setPrecioARS(double precioARS) {
        this.precioARS = precioARS;
    }

    public void setVariedades(List<Varietal> variedades) {
        this.variedades = variedades;
    }

    public Image getImagenEtiqueta() {
        return imagenEtiqueta;
    }

    public void setImagenEtiqueta(Image image) {
        this.imagenEtiqueta = image;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fecha) {
        this.fechaActualizacion = fecha;
    }

    /*------------------------- PARA CREAR NUEVO VINO -----------------------*/
    public Maridaje getMaridaje() {
        return maridaje;
    }

    public void setMaridaje(Maridaje maridaje) {
        this.maridaje = maridaje;
    }

    public List<Varietal> getVariedades() {
        return variedades;
    }

    public int getAnianada() {
        return aniada;
    }
    // endregion
}