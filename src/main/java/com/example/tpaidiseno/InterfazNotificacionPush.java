package com.example.tpaidiseno;

import com.example.tpaidiseno.Entidades.Vino;
import com.example.tpaidiseno.Interfaces.IObserverNotificacionActualizacion;

import java.util.Arrays;
import java.util.Date;

public class InterfazNotificacionPush implements IObserverNotificacionActualizacion {

    public InterfazNotificacionPush() {
    }

    @Override
    public void notificarActualizacionesDeVino(String nomBodega,String nomUsuario, Date fecha, Vino[] listadoVino) {
        System.out.println("Hola, "+nomUsuario+" esta Bodega: " + nomBodega +
                " la fecha: " + fecha
                + "Actualizo los siguientes vinos: " + Arrays.toString(listadoVino));

    }
}
