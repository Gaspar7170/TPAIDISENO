package com.example.tpaidiseno.Interfaces;

import com.example.tpaidiseno.Entidades.Vino;

import java.util.Date;

public interface ISujetoNotificacionActualizacion {
    void notificar(String nomBodega, Date fecha, Vino[] listadoVino);
    void quitar(IObserverNotificacionActualizacion observador);
    void subscribir(IObserverNotificacionActualizacion observador);
}
