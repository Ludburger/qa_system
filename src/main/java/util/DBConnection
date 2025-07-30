
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Fornece conexão JDBC com MySQL
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/qa_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Retorna uma conexão ativa
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
