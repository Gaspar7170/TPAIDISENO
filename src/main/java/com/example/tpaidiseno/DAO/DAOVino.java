package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.Entidades.Vino;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOVino {

    public static List<Vino> getAll(){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,bodega_id,anio,fecha_caducidad,comentarios,nombre,descripcion,precio,maridaje_id FROM vinos ";
        List<Vino> vinos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Vino vino = new Vino();

                vino.setBodega(DAOBodega.getById(rs.getInt("bodega_id")));
                vino.setAniada(rs.getInt("anio"));
                vino.setNombre(rs.getString("nombre"));
                vino.setNotaDeCataBodega(rs.getString("descripcion"));
                vino.setPrecioARS(rs.getDouble("precio"));
                vino.setMaridaje(DAOMaridaje.getById(rs.getInt("maridaje_id")));

                vinos.add(vino);
            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return vinos;
    }

    public static Usuario getById(int id){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,contraseña,es_admin FROM usuarios WHERE id = ?";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.first()){

                usuario.setNombre(rs.getString("nombre"));
                usuario.setContrasenia(rs.getString("contraseña"));
                usuario.setPremium(rs.getBoolean("es_admin"));

            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return usuario;
    }


}
