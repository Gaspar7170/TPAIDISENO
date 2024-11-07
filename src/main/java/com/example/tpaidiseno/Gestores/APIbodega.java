package com.example.tpaidiseno.Gestores;

import com.example.tpaidiseno.DAO.DAOBodega;
import com.example.tpaidiseno.DAO.DAOMaridaje;
import com.example.tpaidiseno.DAO.DAOVarietal;
import com.example.tpaidiseno.Entidades.Vino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIbodega {

    private int id;
    public static List<Vino> obtenerActualizacionVinos(){
        List<Vino> vinos = new ArrayList<>();
        Vino vino1 = new Vino();
        vino1.setBodega(DAOBodega.getById(1));
        vino1.setFechaActualizacion(LocalDate.now());
        vino1.setAniada(2019);
        vino1.setPrecioARS(2000);
        vino1.setNotaDeCataBodega("Muy Rico");
        vino1.setMaridaje(DAOMaridaje.getMaridajeXVino(1));
        vino1.setVarietales(DAOVarietal.getVarietalesXVino(1));
        vino1.setNombre("Santa Ana");
        Vino vino2 = new Vino();
        vino2.setBodega(DAOBodega.getById(2));
        vino2.setFechaActualizacion(LocalDate.now());
        vino2.setAniada(2012);
        vino2.setNombre("Cavani Wine");
        vino2.setPrecioARS(1600);
        vino2.setNotaDeCataBodega("Horrible");
        vino2.setMaridaje(DAOMaridaje.getMaridajeXVino(2));
        vino2.setVarietales(DAOVarietal.getVarietalesXVino(2));
        vinos.add(vino1);
        vinos.add(vino2);
        return vinos;

    }
}

/*
Uso de los datos
Para convertir estos valores a objetos Vino, necesitarás parsear los datos:

Fecha: Usa LocalDate.parse() o construye la fecha con LocalDate.of().
Precio: Convierte de String a double.
Maridajes y Varietales: Si tienes un mapa de IDs a objetos, puedes buscar y asignar estos valores.
 */
