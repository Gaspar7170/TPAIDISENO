package com.example.tpaidiseno.Entidades;


import java.time.LocalDateTime;
import java.util.List;

public class Bodega {
    private String coordenadasUbicacion;
    private String descripcion;
    private String historia;
    private LocalDateTime fechaUltimaActualizacion;
    private String nombre;
    // Este atributo representa los meses para actualizar
    private int periodoActualizacion;

    public Bodega() {
    }

    public Bodega(String nombre, LocalDateTime fechaUltima, int periodo) {
        this.nombre = nombre;
        this.fechaUltimaActualizacion = fechaUltima;
        this.periodoActualizacion = periodo;
    }

    // region Paso 2 del Caso de Uso
    public boolean estaEnPeriodoDeActualizacion(LocalDateTime hoy) {
        // Calculamos la fecha la ultima fecha de actualizacion mas periodo actualizacion (meses) y lo comparamos con la fecha de hoy
        return hoy.isAfter(fechaUltimaActualizacion.plusMonths(periodoActualizacion));
    }

    public String getCoordenadasUbicacion() {
        return coordenadasUbicacion;
    }

    public void setCoordenadasUbicacion(String coordenadasUbicacion) {
        this.coordenadasUbicacion = coordenadasUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(LocalDateTime fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeriodoActualizacion() {
        return periodoActualizacion;
    }

    public void setPeriodoActualizacion(int periodoActualizacion) {
        this.periodoActualizacion = periodoActualizacion;
    }

    // endregion

    // region Paso 5 y 6 del Caso de Uso
    public void actualizarDatosVinos(List<Vino> vinosDeBodega, Vino vinoActualizado) {
        for (Vino vinosBodega : vinosDeBodega) {
            if (vinoActualizado.equals(vinosBodega)) {
                if (vinosBodega.sosVinoParaActualizar()) {
                    // Debemos crear todos los get porque sus atributos son privados
                    vinosBodega.setPrecioARS(vinoActualizado.getPrecioARS());
                    vinosBodega.setNotaDeCataBodega(vinoActualizado.getNotaDeCataBodega());
                    vinosBodega.setImagenEtiqueta(vinoActualizado.getImagenEtiqueta());
                    // Esto lo dejamos porque sino no se actualizaria
                    LocalDateTime fechaActualizarNueva = LocalDateTime.now().plusMonths(periodoActualizacion);
                    vinosBodega.setFechaActualizacion(fechaActualizarNueva);
                    return; // Si lo encuentra pero no se actualiza se corta el ciclo
                }
            }
        }
    }

    public void setUltimaActualizacion(LocalDateTime fecha) {
        this.fechaUltimaActualizacion = fecha;
    }
    // endregion
}