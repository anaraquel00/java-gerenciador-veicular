package br.com.fuctura.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static Connection connection = null;
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    private JDBCUtils() {
        // Construtor privado para evitar instância externa
    }

    public static Connection getConnection() {
        if (connection == null) {
            System.out.println("Carregando Conexão...");
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao conectar com o banco de dados", e);
            }
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
