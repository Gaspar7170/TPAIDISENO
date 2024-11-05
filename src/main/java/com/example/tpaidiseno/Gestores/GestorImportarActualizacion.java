package com.example.tpaidiseno.Gestores;


import com.example.tpaidiseno.Entidades.*;
import com.example.tpaidiseno.Pantallas.PantallaImportarActualizacion;
//import BonVinoGrupo12.Modelo.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GestorImportarActualizacion {

    // Atributos
    private final PantallaImportarActualizacion pantalla;
    private final List<Bodega> listadoBodegasCompleto = new ArrayList<>();
    private final List<String> nombresBodegaActualizar = new ArrayList<>();
    private final List<Bodega> listadoParaActualizar = new ArrayList<>();
    private final List<Vino> listadoVinosActualizarCompleto = new ArrayList<>();
    private List<Vino> listadoVinosActualizarBodega = new ArrayList<>();
    private Bodega BodegaSeleccionada;
    private final Map<Bodega, List<Vino>> bodegasXvino = new HashMap<>();
    private final List<Maridaje> listadoMaridajeCompleto = new ArrayList<>();
    private final List<TipoUva> listadoTipoUvaCompleto = new ArrayList<>();
    private final List<Varietal> listadoVariedadesCompleto = new ArrayList<>();
    private final List<Enofilo> listadoEnofilosAplicacion = new ArrayList<>();
    private boolean activarAlternativo3 = false;

    // Constructor
    public GestorImportarActualizacion(PantallaImportarActualizacion pan) {
        this.pantalla = pan;
    }

    // Paso 1 del Caso de Uso
    public void opcionImportarActualizacion() {
        crearListadoVinos();
        buscarBodegasParaActualizar();
    }

    // Paso 2 del Caso de Uso
    private void buscarBodegasParaActualizar() {
        LocalDate fechaHoy = getFechaActual();
        listadoParaActualizar.clear();
        nombresBodegaActualizar.clear();

        for (Bodega bod : listadoBodegasCompleto) {
            if (bod.estaEnPeriodoDeActualizacion(LocalDateTime.from(fechaHoy))) {
                nombresBodegaActualizar.add(bod.getNombre());
                listadoParaActualizar.add(bod);
            }
        }
        pantalla.mostrarBodegasParaActualizar(nombresBodegaActualizar);
    }

    private LocalDate getFechaActual() {
        return LocalDate.now();
    }

    // Paso 4 del Caso de Uso
    public void tomarSeleccionBodega(String nombre) {
        obtenerActualizacionVinosBodega(nombre);
    }

    private void obtenerActualizacionVinosBodega(String nombre) {
        listadoVinosActualizarBodega = obtenerActualizacionVinos(nombre);

        BodegaSeleccionada = listadoParaActualizar.stream()
                .filter(b -> b.getNombre().equals(nombre))
                .findFirst()
                .orElse(new Bodega());

        if (BodegaSeleccionada == null || BodegaSeleccionada.getNombre() == null) return;

        if (!listadoVinosActualizarBodega.isEmpty()) actualizarOCrearVinos();
    }

    private List<Vino> obtenerActualizacionVinos(String nombre) {
        if (activarAlternativo3) {
            JOptionPane.showMessageDialog(null, "ERROR: Imposible conectarse con la API, intente más tarde", "ERROR", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        } else {
            return listadoVinosActualizarCompleto.stream()
                    .filter(v -> v.getBodega().getNombre().equals(nombre))
                    .collect(Collectors.toList());
        }
    }

    private boolean existeEsteVino(Vino vinoActualizado) {
        if (bodegasXvino.containsKey(vinoActualizado.getBodega())) {
            return bodegasXvino.get(vinoActualizado.getBodega()).stream()
                    .anyMatch(v -> v.getNombre().equals(vinoActualizado.getNombre()));
        } else {
            return false;
        }
    }

    // Paso 5 y 6 del Caso de Uso
    private void actualizarOCrearVinos() {
        List<String[]> resumenVinos = new ArrayList<>();

        for (Vino vinoActualizar : listadoVinosActualizarBodega) {
            boolean alternativa = existeEsteVino(vinoActualizar);

            String estado = alternativa ? "ACTUALIZADO" : "CREADO";

            if (alternativa) {
                actualizarCaracteristicaVinoExistente(vinoActualizar);
            } else {
                crearVinoNuevo(vinoActualizar);
            }

            resumenVinos.add(new String[]{
                    vinoActualizar.getBodega().getNombre(),
                    vinoActualizar.getNombre(),
                    vinoActualizar.getNota(),
                    String.valueOf(vinoActualizar.getPrecio()),
                    vinoActualizar.getVariedades().get(0).getDescripcion(),
                    estado
            });
        }

        BodegaSeleccionada.setUltimaActualizacion(LocalDateTime.from(LocalDate.now()));
        pantalla.mostrarResumenVinos(resumenVinos);
        buscarSeguidoresBodega();
    }

    private void actualizarCaracteristicaVinoExistente(Vino vinoActualizar) {
        List<Vino> listadoVinosXBodega = bodegasXvino.get(vinoActualizar.getBodega());
        BodegaSeleccionada.actualizarDatosVinos(listadoVinosXBodega, vinoActualizar);
    }

    private void crearVinoNuevo(Vino vinoACrear) {
        Maridaje maridajeActual = buscarMaridaje(vinoACrear.getMaridaje());
        TipoUva tipoUva = buscarTipoUva(vinoACrear.getVariedades().get(0).getTipoUva());

        if (maridajeActual != null && tipoUva != null) CrearVino(vinoACrear, tipoUva, maridajeActual);
    }

    private Maridaje buscarMaridaje(Maridaje maridajeBuscar) {
        return listadoMaridajeCompleto.stream()
                .filter(m -> m.esMaridaje(maridajeBuscar))
                .findFirst()
                .orElse(null);
    }

    private TipoUva buscarTipoUva(TipoUva tipoUva) {
        return listadoTipoUvaCompleto.stream()
                .filter(u -> u.esTipoUva(tipoUva))
                .findFirst()
                .orElse(null);
    }

    private Vino CrearVino(Vino vin, TipoUva tip, Maridaje mar) {
        Varietal vari = vin.getVariedades().get(0);
        vari.setTipoUva(tip);

        Vino vinoNuevo = new Vino(
                BodegaSeleccionada,
                vin.getAnianada(),
                vin.getFechaActualizacion(),
                vin.getImagenEtiqueta(),
                vin.getNombre(),
                vin.getNota(),
                vin.getPrecio(),
                mar,
                List.of(vari)
        );

        List<Vino> listadoVinosXBodega = bodegasXvino.getOrDefault(vinoNuevo.getBodega(), new ArrayList<>());
        listadoVinosXBodega.add(vinoNuevo);
        bodegasXvino.put(vinoNuevo.getBodega(), listadoVinosXBodega);

        return vinoNuevo;
    }

    // Paso 7 del Caso de Uso
    private void buscarSeguidoresBodega() {
        List<Enofilo> listadoUsuarioANotificar = listadoEnofilosAplicacion.stream()
                .filter(e -> e.sigueABodega(BodegaSeleccionada))
                .collect(Collectors.toList());

        listadoUsuarioANotificar.forEach(this::notificarActualizacionesDeVino);

        JOptionPane.showMessageDialog(null, "ÉXITO: los mensajes fueron enviados correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
    }

    private void notificarActualizacionesDeVino(Enofilo notificacion) {
        // Implementación futura con Observer (TP3)
    }

    private void crearListadoVinos() {
        // Lógica para crear listado de vinos
    }
}
