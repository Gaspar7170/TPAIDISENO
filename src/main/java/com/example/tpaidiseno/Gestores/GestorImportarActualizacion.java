package com.example.tpaidiseno.Gestores;


import com.example.tpaidiseno.DAO.*;
import com.example.tpaidiseno.Entidades.*;
import com.example.tpaidiseno.Interfaces.IObserverNotificacionActualizacion;
import com.example.tpaidiseno.Interfaces.ISujetoNotificacionActualizacion;
import com.example.tpaidiseno.PantallaImportarActualizacion;
//import BonVinoGrupo12.Modelo.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GestorImportarActualizacion implements ISujetoNotificacionActualizacion {

    // Atributos
    private final PantallaImportarActualizacion pantalla;
    private List<Bodega> listadoBodegasCompleto = new ArrayList<>();
    private final List<Bodega> bodegasActualizar = new ArrayList<>();
    private final List<Bodega> listadoParaActualizar = new ArrayList<>();
    private List<Vino> listadoVinosActualizarCompleto = new ArrayList<>();
    private List<Vino>  listadoVinosActualizarBodega ;
    private Bodega BodegaSeleccionada;
    private final Map<Bodega, List<Vino>> bodegasXvino = new HashMap<>();
    private final List<Maridaje> listadoMaridajeCompleto = new ArrayList<>();
    private final List<TipoUva> listadoTipoUvaCompleto = new ArrayList<>();
    private final List<Varietal> listadoVariedadesCompleto = new ArrayList<>();
    private List<Enofilo> listadoEnofilosAplicacion = new ArrayList<>();
    private boolean activarAlternativo3 = false;
    private List<IObserverNotificacionActualizacion> observers = new ArrayList<>();

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
        bodegasActualizar.clear();



        for (Bodega bod : listadoBodegasCompleto) {
            if (bod.estaEnPeriodoDeActualizacion(fechaHoy)) {
                bodegasActualizar.add(bod);
                listadoParaActualizar.add(bod);
            }
        }
        pantalla.mostrarBodegasParaActualizar(bodegasActualizar);
    }

    private LocalDate getFechaActual() {
        return LocalDate.now();
    }

    // Paso 4 del Caso de Uso
    public void tomarSeleccionBodega(Bodega bodegaSeleccionada) {
        BodegaSeleccionada = bodegaSeleccionada;
        obtenerActualizacionVinosBodega(bodegaSeleccionada);
    }

    private void obtenerActualizacionVinosBodega(Bodega bodega) {
        listadoVinosActualizarBodega = APIbodega.obtenerActualizacionVinos();



    //    listadoVinosActualizarBodega = listado

        /*BodegaSeleccionada = listadoParaActualizar.stream()
                .filter(b -> b.getNombre().equals(nombre))
                .findFirst()
                .orElse(new Bodega());*/



        if (listadoVinosActualizarBodega != null) actualizarOCrearVinos();
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
        List<Object> resumenVinos = new ArrayList<>();

        for (Vino vinoActualizar : listadoVinosActualizarBodega) {

            String estado ;

            if (existeEsteVino(vinoActualizar)) {
                actualizarCaracteristicaVinoExistente(vinoActualizar);
                estado = "ACTUALIZADO";

            } else {
                crearVinoNuevo(vinoActualizar);
                estado = "CREADO";
            }

            resumenVinos.add(new String[]{
                    vinoActualizar.getBodega().getNombre(),
                    vinoActualizar.getNombre(),
                    vinoActualizar.getNotaDeCataBodega(),
                    String.valueOf(vinoActualizar.getPrecioARS()),
                    //vinoActualizar.getVarietales().get(0).getDescripcion(),
                    estado
            });
        }

        BodegaSeleccionada.setUltimaActualizacion(getFechaActual());
        pantalla.mostrarResumenVinos(resumenVinos);
        buscarSeguidoresBodega();
    }

    private void actualizarCaracteristicaVinoExistente(Vino vinoActualizar) {
        List<Vino> listadoVinosXBodega = bodegasXvino.get(vinoActualizar.getBodega());
        BodegaSeleccionada.actualizarDatosVinos(listadoVinosXBodega, vinoActualizar);
    }

    private void crearVinoNuevo(Vino vinoACrear) {
        int ultimo = DAOVino.getLast();
        DAOVarietal.insertarVarietaje(ultimo,vinoACrear.getVarietales());
        DAOMaridaje.insertarMaridaje(ultimo,vinoACrear.getMaridaje());
        DAOVino.insertarVinoNvo(vinoACrear);


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

    }

    private void crearListadoVinos() {

        listadoBodegasCompleto = DAOBodega.getAll();

        listadoVinosActualizarCompleto = DAOVino.getAll();


        listadoEnofilosAplicacion = DAOEnofilo.getAll();
    }


    @Override
    public void notificar(String nomBodega, String nomUsuario,Date fecha, Vino[] listadoVino) {
        for (IObserverNotificacionActualizacion observador : observers){
            observador.notificarActualizacionesDeVino(nomBodega,nomUsuario,fecha,listadoVino);
        }

    }

    @Override
    public void quitar(IObserverNotificacionActualizacion observador) {
        observers.remove(observador);

    }

    @Override
    public void subscribir(IObserverNotificacionActualizacion observador) {
        observers.add(observador);

    }
}
