package com.example.tpaidiseno.Interfaces;

import com.example.tpaidiseno.Entidades.Vino;

import java.util.Date;

public interface IObserverNotificacionActualizacion {
    void notificarActualizacionesDeVino(String nomBodega, Date fecha, Vino[] listadoVino);
}
