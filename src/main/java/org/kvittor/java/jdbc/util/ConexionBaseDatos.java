package org.kvittor.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String username = "root";
    private static String password = "admin";
    private static String url = "jdbc:mysql://localhost:3306/proyecto_contenedor_usuario?severTimezone=America/Argentina/Buenos_Aires";

    public static Connection getInstance() throws SQLException {

        return DriverManager.getConnection(url,username,password);

    }


}
