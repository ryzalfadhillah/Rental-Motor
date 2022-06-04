package Connection;

/**
 *
 * @author Ryzal
 */

import java.sql.*;
public class Koneksi {
    String DBurl = "jdbc:mysql://localhost/rentloyo";
    String DBusername = "root";
    String DBpassword = "";
    
    public Connection connection;
    public Statement statement;

    public Koneksi() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            System.out.println("Connection Failed\n" + ex.getMessage());
        }
    }
    
    
}
