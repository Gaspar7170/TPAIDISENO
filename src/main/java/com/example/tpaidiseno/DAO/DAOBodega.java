package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Bodega;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBodega {
    public static List<Bodega> getAll() {
        List<Bodega> bodegas = new ArrayList<>();
        String sql = "SELECT id, nombre, fecha_ultima_actualizacion, periodo FROM bodegas";

        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bodega bodega = new Bodega();
                bodega.setNombre(rs.getString("nombre"));
                bodegas.add(bodega);
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }

        return bodegas;
    }


    public static Bodega getById(int id){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre FROM bodegas WHERE id = ?";
        Bodega bodega = new Bodega();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.first()){

                bodega.setNombre(rs.getString("nombre"));


            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return bodega;
    }

}
