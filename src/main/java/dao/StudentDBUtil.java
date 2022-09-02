package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class StudentDBUtil {

    private static String jdbcURL = "jdbc:mysql://localhost:3306/student";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";
    private static Connection con;

    public static Connection getConnection() {

        try {
            //Establishing a Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}
