package com.example.tpaidiseno.Pantallas;

import com.example.tpaidiseno.Entidades.Vino;
import com.example.tpaidiseno.Interfaces.IObserverNotificacionActualizacion;

import java.util.Arrays;
import java.util.Date;

public class NotificacionVinoActualizado implements IObserverNotificacionActualizacion {

    public NotificacionVinoActualizado() {
    }

    @Override
    public void notificarActualizacionesDeVino(String nomBodega, Date fecha, Vino[] listadoVino) {
        System.out.println("Bodega: " + nomBodega +
                " la fecha: " + fecha
                + "Actualizo los siguientes vinos: " + Arrays.toString(listadoVino));

    }
}
