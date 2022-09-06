package StudentPackage.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class StudentDBUtil {


    public static void setUrl(String url) {
        Url = url;
    }

    public static void setUsername(String username) {
        Username = username;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    private static String Url;
    private static String Username;
    private static String Password;


    /*private static String jdbcURL = "jdbc:mysql://localhost:3306/student";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";*/
    private static Connection con;

    public static Connection getConnection() {
        System.out.println( Url + Username + Password );
        try {
            //Establishing a Connection
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            con = DriverManager.getConnection( Url, Username, Password );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }



}



