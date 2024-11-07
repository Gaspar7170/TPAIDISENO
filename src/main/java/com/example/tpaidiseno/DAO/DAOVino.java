package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.*;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOVino {

    public static List<Vino> getAll() {
        List<Vino> vinos = new ArrayList<>();
        String sql = "SELECT id, bodega_id, anio, fecha_actualizacion, nombre, descripcion, precio FROM vinos";

        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vino vino = new Vino();
                vino.setId(rs.getInt("id"));
                vino.setBodega(DAOBodega.getById(rs.getInt("bodega_id")));
                vino.setAniada(rs.getInt("anio"));
                vino.setNombre(rs.getString("nombre"));
                vino.setNotaDeCataBodega(rs.getString("descripcion"));
                vino.setPrecioARS(rs.getDouble("precio"));
                vino.setMaridaje(DAOMaridaje.getMaridajeXVino(rs.getInt("id")));
                vino.setFechaActualizacion(LocalDate.parse(rs.getString("fecha_actualizacion")));
                vino.setVarietales(DAOVarietal.getVarietalesXVino(rs.getInt("id")));

                vinos.add(vino);
            }
        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return vinos;
    }

    public static Vino getById(int id) {
        Vino vino = new Vino();
        String sql = "SELECT id, bodega_id, anio, fecha_actualizacion, nombre, descripcion, precio FROM vinos WHERE id = ?";

        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    vino.setId(rs.getInt("id"));
                    vino.setBodega(DAOBodega.getById(rs.getInt("bodega_id")));
                    vino.setAniada(rs.getInt("anio"));
                    vino.setNombre(rs.getString("nombre"));
                    vino.setNotaDeCataBodega(rs.getString("descripcion"));
                    vino.setPrecioARS(rs.getDouble("precio"));
                    vino.setMaridaje(DAOMaridaje.getMaridajeXVino(rs.getInt("id")));
                    vino.setFechaActualizacion(LocalDate.parse(rs.getString("fecha_actualizacion")));
                    vino.setVarietales(DAOVarietal.getVarietalesXVino(rs.getInt("id")));
                }
            }
        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return vino;
    }

    public static void insertarVinoNvo(Vino vinoACrear) {
        String sql = "INSERT INTO vinos (bodega_id, anio, fecha_actualizacion, nombre, descripcion, precio) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, vinoACrear.getBodega().getId());
            ps.setInt(2, vinoACrear.getAniada());
            ps.setString(3, (Date.valueOf(vinoACrear.getFechaActualizacion())).toString());
            ps.setString(4, vinoACrear.getNombre());
            ps.setString(5, vinoACrear.getNotaDeCataBodega());
            ps.setDouble(6, vinoACrear.getPrecioARS());
            ps.executeUpdate();

            int ultimo = getLast();
            DAOVarietal.insertarVarietaje(ultimo, vinoACrear.getVarietales());
            DAOMaridaje.insertarMaridaje(ultimo, vinoACrear.getMaridaje());
        } catch (SQLException eSql) {

            eSql.printStackTrace();

        }
    }

    public static void actualizarVino(Vino vinoActualizar) {
        String sql = "UPDATE vinos SET precio = ?, descripcion = ? , fecha_actualizacion = ? WHERE nombre = ?";

        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, vinoActualizar.getPrecioARS());
            ps.setString(2, vinoActualizar.getNotaDeCataBodega());
            ps.setString(3, (Date.valueOf(vinoActualizar.getFechaActualizacion())).toString());
            ps.setString(4, vinoActualizar.getNombre());
            ps.executeUpdate();

        } catch (SQLException eSql) {

            eSql.printStackTrace();

        }
    }

    public static int getLast() {
        int ultimo = 0;
        String sql = "SELECT seq FROM sqlite_sequence WHERE name = 'vinos'";

        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                ultimo = rs.getInt("seq");
            }
        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return ultimo;
    }

    public static List<Vino> traerVinosXBodega(Bodega bodega) {

        List<Vino> vinos = new ArrayList<>();
        String sql = "SELECT id, bodega_id, anio, fecha_actualizacion, nombre, descripcion, precio FROM vinos WHERE bodega_id = ?";

        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, bodega.getId());

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Vino vino = new Vino();
                    vino.setId(rs.getInt("id"));
                    vino.setBodega(bodega);
                    vino.setAniada(rs.getInt("anio"));
                    vino.setNombre(rs.getString("nombre"));
                    vino.setNotaDeCataBodega(rs.getString("descripcion"));
                    vino.setPrecioARS(rs.getDouble("precio"));
                    vino.setMaridaje(DAOMaridaje.getMaridajeXVino(rs.getInt("id")));
                    vino.setFechaActualizacion(LocalDate.parse(rs.getString("fecha_actualizacion")));
                    vino.setVarietales(DAOVarietal.getVarietalesXVino(rs.getInt("id")));

                    vinos.add(vino);
                }
            }
        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return vinos;

    }
}
