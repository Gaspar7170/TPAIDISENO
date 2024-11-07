package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Maridaje;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.Entidades.Varietal;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMaridaje {

    public static List<Maridaje> getAll() {
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,descripcion FROM maridajes ";
        List<Maridaje> maridajes = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Maridaje maridaje = new Maridaje();
                maridaje.setId(rs.getInt("id"));
                maridaje.setNombre(rs.getString("nombre"));
                maridaje.setDescripcion(rs.getString("descripcion"));

                maridajes.add(maridaje);
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return maridajes;
    }

    public static Maridaje getById(int id) {

        String sql = "SELECT id,nombre,descripcion FROM maridajes WHERE id = ?";
        Maridaje maridaje = new Maridaje();
        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {

                if (rs.next()) {
                    maridaje.setId(rs.getInt("id"));
                    maridaje.setNombre(rs.getString("nombre"));
                    maridaje.setDescripcion(rs.getString("descripcion"));
                }
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return maridaje;
    }

    public static List<Maridaje> getMaridajeXVino(int id) {

        String sql = "SELECT maridaje_id, vino_id FROM maridajes_x_vino WHERE vino_id = ? ";
        List<Maridaje> maridajes = new ArrayList<>();

        try(Connection con = SQLiteConnection.connect();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                Maridaje maridaje = getById(rs.getInt("maridaje_id"));

                maridajes.add(maridaje);
            }}

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }

        return maridajes;
    }

    public static void insertarMaridaje(int last, List<Maridaje> maridajes) {

        String sql = "INSERT INTO maridajes_x_vino (vino_id,maridaje_id) VALUES(?,?)";
        try(Connection con = SQLiteConnection.connect();
            PreparedStatement ps = con.prepareStatement(sql);){

            for (Maridaje m : maridajes) {
                ps.setInt(1, last);
                ps.setInt(2, m.getId());
                ps.executeUpdate();
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }

    }

}
