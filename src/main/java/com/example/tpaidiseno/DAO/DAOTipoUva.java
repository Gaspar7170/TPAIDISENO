package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.TipoUva;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTipoUva {

    public static List<TipoUva> getAll() {
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,descripcion FROM tipos_uva ";
        List<TipoUva> tiposUvas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TipoUva tipoUva = new TipoUva();
                tipoUva.setId(rs.getInt("id"));
                tipoUva.setNombre(rs.getString("nombre"));
                tipoUva.setDescripcion(rs.getString("descripcion"));
                tiposUvas.add(tipoUva);
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return tiposUvas;
    }

    public static TipoUva getById(int id) {

        String sql = "SELECT id,nombre,descripcion FROM tipos_uva WHERE id = ?";
        TipoUva tipoUva = new TipoUva();
        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {

                if (rs.next()) {
                    tipoUva.setId(rs.getInt("id"));
                    tipoUva.setNombre(rs.getString("nombre"));
                    tipoUva.setDescripcion(rs.getString("descripcion"));
                }
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }
        return tipoUva;
    }

}
