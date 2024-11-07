package com.example.tpaidiseno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    private static final String URL = "jdbc:sqlite:./BD.db?busy_timeout=5000"; // Ruta a tu archivo SQLite

    public static Connection connect() {
        Connection conn = null;
        try {
            // Establecer la conexión
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión exitosa a la base de datos SQLite.");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return conn;
    }


}
