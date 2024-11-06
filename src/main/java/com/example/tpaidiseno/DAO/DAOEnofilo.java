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
    public static List<Enofilo> getAll(){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,apellido,nombre,usuario_id FROM enofilos ";
        List<Enofilo> enofilos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Enofilo enofilo = new Enofilo();
                enofilo.setNombre(rs.getString("nombre"));
                enofilo.setApellido(rs.getString("apellido"));
                enofilo.setUsuario(DAOUsuario.getById(rs.getInt("usuario_id")));
                enofilo.setSeguido(DAOSiguiendo.getAllOfEnofilo(rs.getInt("id")));

                enofilos.add(enofilo);
            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return enofilos;
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
