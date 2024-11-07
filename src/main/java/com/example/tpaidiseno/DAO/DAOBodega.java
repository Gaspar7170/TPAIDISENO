package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Bodega;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAOBodega {
    public static List<Bodega> getAll() {
        List<Bodega> bodegas = new ArrayList<>();
        String sql = "SELECT id, nombre, fecha_ultima_actualizacion, periodo FROM bodegas";

        try (Connection con = SQLiteConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bodega bodega = new Bodega();
                bodega.setId(rs.getInt("id"));
                bodega.setNombre(rs.getString("nombre"));
                bodega.setUltimaActualizacion(LocalDate.parse(rs.getString("fecha_ultima_actualizacion")));
                bodega.setPeriodoActualizacion(rs.getInt("periodo"));
                bodegas.add(bodega);
            }

        } catch (SQLException eSql) {
            eSql.printStackTrace();
        }

        return bodegas;
    }


    public static Bodega getById(int id){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,fecha_ultima_actualizacion,periodo FROM bodegas WHERE id = ?";
        Bodega bodega = new Bodega();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                bodega.setId(rs.getInt("id"));
                bodega.setNombre(rs.getString("nombre"));
                bodega.setUltimaActualizacion(LocalDate.parse(rs.getString("fecha_ultima_actualizacion")));
                bodega.setPeriodoActualizacion(rs.getInt("periodo"));


            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return bodega;
    }

}
