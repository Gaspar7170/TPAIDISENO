package com.example.tpaidiseno.Interfaces;

import com.example.tpaidiseno.Entidades.Vino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public interface ISujetoNotificacionActualizacion {
    void notificar(String nomBodega, LocalDate fecha, ArrayList<Vino> listadoVino);
    void quitar(IObserverNotificacionActualizacion observador);
    void subscribir(IObserverNotificacionActualizacion observador);
}
