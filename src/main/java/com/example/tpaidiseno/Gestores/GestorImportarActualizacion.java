package com.example.tpaidiseno.Gestores;


import com.example.tpaidiseno.DAO.*;
import com.example.tpaidiseno.Entidades.*;
import com.example.tpaidiseno.Interfaces.IObserverNotificacionActualizacion;
import com.example.tpaidiseno.Interfaces.ISujetoNotificacionActualizacion;
import com.example.tpaidiseno.InterfazNotificacionPush;
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

    private List<Vino> listadoVinosXBodega = new ArrayList<>();
    private List<Vino> listadoVinosActualizarBodega;
    private Map<Bodega, List<Vino>> hashVinosXBodega = new HashMap<>();
    private Map<Bodega, List<Vino>> hashVinosActualizadoXBodega = new HashMap<>();
    private Bodega bodegaSeleccionada;


    private List<Enofilo> listadoEnofilosAplicacion = new ArrayList<>();
    private boolean activarAlternativo3 = false;
    private List<IObserverNotificacionActualizacion> observers = new ArrayList<>();

    // Constructor
    public GestorImportarActualizacion(PantallaImportarActualizacion pan) {
        this.pantalla = pan;
    }
    private void crearListadoVinos() {

        listadoBodegasCompleto = DAOBodega.getAll();
        for (Bodega b : listadoBodegasCompleto) {
            listadoVinosXBodega = DAOVino.traerVinosXBodega(b);
            hashVinosXBodega.put(b, listadoVinosXBodega);
            hashVinosActualizadoXBodega.put(b,new ArrayList<Vino>());
        }


        listadoEnofilosAplicacion = DAOEnofilo.getAll();
    }


    // Paso 1 del Caso de Uso
    public void opcionImportarActualizacion() {
        crearListadoVinos();
        buscarBodegasParaActualizar();
    }

    // Paso 2 del Caso de Uso
    private void buscarBodegasParaActualizar() {
        LocalDate fechaHoy = getFechaActual();

        bodegasActualizar.clear();


        for (Bodega bod : listadoBodegasCompleto) {
            if (bod.estaEnPeriodoDeActualizacion(fechaHoy)) {
                bodegasActualizar.add(bod);

            }
        }
        pantalla.mostrarBodegasParaActualizar(bodegasActualizar);
    }

    private LocalDate getFechaActual() {
        return LocalDate.now();
    }

    // Paso 4 del Caso de Uso
    public void tomarSeleccionBodega(Bodega bodegaSeleccionada) {
        this.bodegaSeleccionada = bodegaSeleccionada;
        obtenerActualizacionVinosBodega();
    }

    private void obtenerActualizacionVinosBodega() {
        listadoVinosActualizarBodega = APIbodega.obtenerActualizacionVinos();

        if (listadoVinosActualizarBodega != null) actualizarOCrearVinos();
    }

    private boolean existeEsteVino(Vino vinoActualizar) {
        List<Vino> listadoVinosActualizar = hashVinosXBodega.get(vinoActualizar.getBodega());

        // Si no hay vinos para esa bodega, retornamos false
        if (listadoVinosActualizar == null) {
            return false;
        }

        // Recorremos la lista de vinos de esa bodega
        for (Vino vino : listadoVinosActualizar) {
            // Comparamos el nombre del vino actual con el nombre del vino a actualizar
            if (vino.getNombre().equals(vinoActualizar.getNombre())) {
                return true; // Existe un vino con el mismo nombre en la misma bodega
            }
        }

        return false;
    }

    // Paso 5 y 6 del Caso de Uso
    private void actualizarOCrearVinos() {
        List<String> resumenVinos = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (Vino vinoActualizar : listadoVinosActualizarBodega) {

            String estado;

            if (existeEsteVino(vinoActualizar)) {
                actualizarCaracteristicaVinoExistente(vinoActualizar);
                estado = "ACTUALIZADO";

            } else {
                crearVinoNuevo(vinoActualizar);
                estado = "CREADO";
            }
            hashVinosActualizadoXBodega.get(bodegaSeleccionada).add(vinoActualizar);
            sb.append(vinoActualizar.getBodega().getNombre() + " ");
            sb.append(vinoActualizar.getNombre() + " ");
            sb.append(vinoActualizar.getNotaDeCataBodega() + " ");
            sb.append(vinoActualizar.getPrecioARS() + " ");
            sb.append(vinoActualizar.getVarietales() + " ");
            sb.append(estado);
            System.out.println(sb.toString());
            resumenVinos.add(sb.toString());
        }

        bodegaSeleccionada.setUltimaActualizacion(getFechaActual());
        DAOBodega.actualizarFechaActualizacion(bodegaSeleccionada);
        pantalla.mostrarResumenVinos(resumenVinos);
        buscarSeguidoresBodega();
    }

    private void actualizarCaracteristicaVinoExistente(Vino vinoActualizar) {
        List<Vino> listadoVinosXBodega = hashVinosXBodega.get(vinoActualizar.getBodega());
        bodegaSeleccionada.actualizarDatosVinos(listadoVinosXBodega, vinoActualizar);
        DAOVino.actualizarVino(vinoActualizar);

    }

    private void crearVinoNuevo(Vino vinoACrear) {
        DAOVino.insertarVinoNvo(vinoACrear);
    }


    // Paso 7 del Caso de Uso
    private void buscarSeguidoresBodega() {
        List<Enofilo> listadoUsuarioANotificar = listadoEnofilosAplicacion.stream()
                .filter(e -> e.sigueABodega(bodegaSeleccionada))

                .collect(Collectors.toList());
        for (Enofilo e : listadoUsuarioANotificar){
            subscribir(new InterfazNotificacionPush(e.getUsuario()));
        }

        notificar(bodegaSeleccionada.getNombre(),getFechaActual(), hashVinosActualizadoXBodega.get(bodegaSeleccionada));

        JOptionPane.showMessageDialog(null, "ÉXITO: los mensajes fueron enviados correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
    }





    @Override
    public void notificar(String nomBodega, Date fecha, ArrayList<Vino> listadoVino) {
        for (IObserverNotificacionActualizacion observador : observers) {
            observador.notificarActualizacionesDeVino(nomBodega, fecha, listadoVino);
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
