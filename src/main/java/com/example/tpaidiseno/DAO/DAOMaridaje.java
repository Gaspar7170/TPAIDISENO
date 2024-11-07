package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Maridaje;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMaridaje {

    public static List<Maridaje> getAll(){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,descripcion FROM maridajes ";
        List<Maridaje> maridajes = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Maridaje maridaje = new Maridaje();
                maridaje.setNombre(rs.getString("nombre"));
                maridaje.setDescripcion(rs.getString("descripcion"));

                maridajes.add(maridaje);
            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return maridajes;
    }

    public static Maridaje getById(int id){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,descripcion FROM maridajes WHERE id = ?";
        Maridaje maridaje = new Maridaje();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){


                maridaje.setNombre(rs.getString("nombre"));
                maridaje.setDescripcion(rs.getString("descripcion"));

            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return maridaje;
    }

}
