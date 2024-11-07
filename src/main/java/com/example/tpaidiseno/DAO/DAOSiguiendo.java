package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Siguiendo;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOSiguiendo {

    public static List<Siguiendo> getAllBodegaOfEnofilo(int id) {

        String sql = "SELECT bodega_id FROM seguimientos WHERE id = ?";
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
