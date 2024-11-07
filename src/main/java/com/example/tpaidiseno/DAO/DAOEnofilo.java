package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Enofilo;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOEnofilo {
    public static List<Enofilo> getAll() {

        String sql = "SELECT id,apellido,nombre,usuario_id FROM enofilos ";
        List<Enofilo> enofilos = new ArrayList<>();
        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {


            while (rs.next()) {
                Enofilo enofilo = new Enofilo();
                enofilo.setNombre(rs.getString("nombre"));
                enofilo.setApellido(rs.getString("apellido"));
                enofilo.setUsuario(DAOUsuario.getById(rs.getInt("usuario_id")));
                enofilo.setSeguido(DAOSiguiendo.getAllBodegaOfEnofilo(rs.getInt("id")));

                enofilos.add(enofilo);
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return enofilos;
    }

    public static Enofilo getById(int id) {

        String sql = "SELECT id,apellido,nombre,usuario_id FROM enofilos WHERE id = ?";
        Enofilo enofilo = new Enofilo();
        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {

                    enofilo.setNombre(rs.getString("nombre"));
                    enofilo.setApellido(rs.getString("apellido"));
                    enofilo.setUsuario(DAOUsuario.getById(rs.getInt("usuario_id")));
                    enofilo.setSeguido(DAOSiguiendo.getAllBodegaOfEnofilo(rs.getInt("id")));

                }
            }


        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return enofilo;
    }

}
