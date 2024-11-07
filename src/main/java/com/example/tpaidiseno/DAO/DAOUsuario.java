package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {

    public static List<Usuario> getAll(){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,contraseña,es_admin FROM usuarios ";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Usuario us = new Usuario();
                us.setNombre(rs.getString("nombre"));
                us.setContrasenia(rs.getString("contraseña"));
                us.setPremium(rs.getBoolean("es_admin"));
                usuarios.add(us);
            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return usuarios;
    }

    public static Usuario getById(int id){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,contraseña,es_admin FROM usuarios WHERE id = ?";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){

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
