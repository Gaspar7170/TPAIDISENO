package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.Entidades.Varietal;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOVarietal {


    public static List<Varietal> getAll(){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,porcentaje,tipo_uva_id FROM varietales ";
        List<Varietal> varietales = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Varietal varietal = new Varietal();
                varietal.setNombre(rs.getString("nombre"));
                varietal.setPorcentajeComposicion(rs.getDouble("porcentaje"));
                varietal.setTipoUva(DAOTipoUva.getById(rs.getInt("tipo_uva_id")));

                varietales.add(varietal);
            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return varietales;
    }

    public static Varietal getById(int id){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,porcentaje,tipo_uva_id FROM varietales WHERE id = ?";
        Varietal varietal = new Varietal();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                varietal.setNombre(rs.getString("nombre"));
                varietal.setPorcentajeComposicion(rs.getDouble("porcentaje"));
                varietal.setTipoUva(DAOTipoUva.getById(rs.getInt("tipo_uva_id")));



            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return varietal;
    }
}
