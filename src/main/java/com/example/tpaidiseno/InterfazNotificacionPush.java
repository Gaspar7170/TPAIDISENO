package com.example.tpaidiseno;

import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.Entidades.Vino;
import com.example.tpaidiseno.Interfaces.IObserverNotificacionActualizacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InterfazNotificacionPush implements IObserverNotificacionActualizacion {
    Usuario us;
    //simula la interfaz de cada uno
    public InterfazNotificacionPush(Usuario usuario) {
        this.us = usuario;
    }

    @Override
    public void notificarActualizacionesDeVino(String nomBodega, Date fecha, ArrayList<Vino> listadoVino) {
        System.out.println("Hola, "+us.getNombre()+" esta Bodega: " + nomBodega +
                " la fecha: " + fecha
                + "Actualizo los siguientes vinos: " + listadoVino.toString());

    }
}
