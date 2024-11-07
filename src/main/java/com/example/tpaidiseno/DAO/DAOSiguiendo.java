package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Siguiendo;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOSiguiendo {

    public static List<Siguiendo> getAll() {

        String sql = "SELECT id,nombre,contraseña,es_admin FROM seguimientos ";
        List<Siguiendo> seguimientos = new ArrayList<>();
        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                Siguiendo seguimiento = new Siguiendo();
                seguimiento.setFechaInicio(rs.getDate("fecha_inicio"));
                seguimiento.setFechaFin(rs.getDate("fecha_fin"));
                seguimiento.setBodega(DAOBodega.getById(rs.getInt("bodega_id")));

                seguimientos.add(seguimiento);
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return seguimientos;
    }

    public static Siguiendo getById(int id) {

        String sql = "SELECT id,fecha_inicio,fecha_fin,bodega_id FROM seguimientos WHERE id = ?";
        Siguiendo seguimiento = new Siguiendo();
        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {

                if (rs.next()) {

                    seguimiento.setFechaInicio(Date.valueOf(rs.getString("fecha_inicio")));
                    seguimiento.setFechaFin(Date.valueOf(rs.getString("fecha_fin")));
                    seguimiento.setBodega(DAOBodega.getById(rs.getInt("bodega_id")));

                }
            }
        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return seguimiento;
    }

    public static List<Siguiendo> getAllBodegaOfEnofilo(int id) {

        String sql = "SELECT bodega_id FROM seguimiento WHERE id = ?";
        List<Siguiendo> seguimientos = new ArrayList<>();
        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {

                while (rs.next()) {
                    Siguiendo seguimiento = new Siguiendo();
                    seguimiento.setBodega(DAOBodega.getById(rs.getInt("bodega_id")));
                    seguimientos.add(seguimiento);
                }
            }
        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return seguimientos;
    }

}
