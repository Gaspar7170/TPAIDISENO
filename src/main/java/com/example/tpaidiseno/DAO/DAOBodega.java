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
    public static List<Bodega> getAll(){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,fecha_fundacion,calificacion FROM bodegas ";
        List<Bodega> bodegas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Bodega bodega = new Bodega();
                bodega.setNombre(rs.getString("nombre"));

                bodegas.add(bodega);
            }

        }catch (SQLException eSql){
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