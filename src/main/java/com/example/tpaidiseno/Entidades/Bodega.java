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

    public String getNombre() {
        return nombre;
    }
    // endregion

    // region Paso 5 y 6 del Caso de Uso
    public void actualizarDatosVinos(List<Vino> vinosDeBodega, Vino vinoActualizado) {
        for (Vino vinosBodega : vinosDeBodega) {
            if (vinoActualizado.equals(vinosBodega)) {
                if (vinosBodega.sosVinoParaActualizar()) {
                    // Debemos crear todos los get porque sus atributos son privados
                    vinosBodega.setPrecio(vinoActualizado.getPrecio());
                    vinosBodega.setNotaCata(vinoActualizado.getNota());
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