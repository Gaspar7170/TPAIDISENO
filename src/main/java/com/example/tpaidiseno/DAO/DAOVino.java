package com.example.tpaidiseno.DAO;

import com.example.tpaidiseno.Entidades.Maridaje;
import com.example.tpaidiseno.Entidades.Usuario;
import com.example.tpaidiseno.Entidades.Varietal;
import com.example.tpaidiseno.Entidades.Vino;
import com.example.tpaidiseno.SQLiteConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOVino {

    public static List<Vino> getAll(){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,bodega_id,anio,fecha_actualizacion,comentarios,nombre,descripcion,precio,maridaje_id FROM vinos ";
        List<Vino> vinos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Vino vino = new Vino();
                vino.setId(rs.getInt("id"));
                vino.setBodega(DAOBodega.getById(rs.getInt("bodega_id")));
                vino.setAniada(rs.getInt("anio"));
                vino.setNombre(rs.getString("nombre"));
                vino.setNotaDeCataBodega(rs.getString("descripcion"));
                vino.setPrecioARS(rs.getDouble("precio"));
                vino.setMaridaje(DAOMaridaje.getMaridajeXVino(rs.getInt("maridaje_id")));
                vino.setFechaActualizacion(LocalDate.parse(rs.getString("fecha_actualizacion")));
                vino.setVarietales(DAOVarietal.getVarietalesXVino(rs.getInt("id")));

                vinos.add(vino);
            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return vinos;
    }

    public static Vino getById(int id){
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT id,nombre,contraseña,es_admin FROM usuarios WHERE id = ?";
        Vino vino = new Vino();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.first()){

                vino.setId(rs.getInt("id"));
                vino.setBodega(DAOBodega.getById(rs.getInt("bodega_id")));
                vino.setAniada(rs.getInt("anio"));
                vino.setNombre(rs.getString("nombre"));
                vino.setNotaDeCataBodega(rs.getString("descripcion"));
                vino.setPrecioARS(rs.getDouble("precio"));
                vino.setMaridaje(DAOMaridaje.getMaridajeXVino(rs.getInt("maridaje_id")));
                vino.setFechaActualizacion(LocalDate.parse(rs.getString("fecha_actualizacion")));
                vino.setVarietales(DAOVarietal.getVarietalesXVino(rs.getInt("id")));

            }

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return vino;
    }


    public static void insertarVinoNvo(Vino vinoACrear) {
        Connection con = SQLiteConnection.connect();
        String sql = "INSERT INTO vinos bodega_id,anio,fecha_actualizacion,nombre,descripcion,precio VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vinoACrear.getBodega().getId());
            ps.setInt(2,vinoACrear.getAniada());
            Date fecha = Date.valueOf(vinoACrear.getFechaActualizacion());
            ps.setDate(3,fecha);
            ps.setString(4,vinoACrear.getNombre());
            ps.setString(5, vinoACrear.getNotaDeCataBodega());
            ps.setDouble(6,vinoACrear.getPrecioARS());
            ps.executeUpdate();
            int ultimo = DAOVino.getLast();
            DAOVarietal.insertarVarietaje(ultimo,vinoACrear.getVarietales());
            DAOMaridaje.insertarMaridaje(ultimo,vinoACrear.getMaridaje());

        }catch (SQLException eSql){
            eSql.printStackTrace();
        }

        //Clases varietal id 1
        vinoACrear.getVarietales();
        //Clases Maridaje id 1
        vinoACrear.getMaridaje();

    }

    private static int getLast() {
        Connection con = SQLiteConnection.connect();
        String sql = "SELECT seq FROM sqlite_sequence WHERE name = 'vinos' ";
        int ultimo = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())ultimo = rs.getInt("seq");
        }catch (SQLException eSql){
            eSql.printStackTrace();
        }
        return ultimo;

    }


}
