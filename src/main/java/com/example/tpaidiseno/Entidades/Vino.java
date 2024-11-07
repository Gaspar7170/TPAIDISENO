package com.example.tpaidiseno.Entidades;


import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

public class Vino {
    // Es aniada es por no utilizar ñ en un atributo
    private Bodega bodega;
    private int aniada;
    private LocalDate fechaActualizacion;
    // Este tambien puede ser byte[]
    private Image imagenEtiqueta;
    private String nombre;
    private String notaDeCataBodega;
    private double precioARS;
    private Maridaje maridaje;
    private List<Varietal> variedades;

    public Vino() {
    }

    public Vino(Bodega bodega, int aniada, LocalDate fechaActualizacion,
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
        return LocalDateTime.now().isAfter(ChronoLocalDateTime.from(fechaActualizacion));
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

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fecha) {
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

    @Override
    public String toString() {
        return "Vino{" +
                "bodega=" + bodega +
                ", aniada=" + aniada +
                ", fechaActualizacion=" + fechaActualizacion +
                ", nombre='" + nombre + '\'' +
                ", notaDeCataBodega='" + notaDeCataBodega + '\'' +
                ", precioARS=" + precioARS +
                ", maridaje=" + maridaje +
                "\n}";
    }
}