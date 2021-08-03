package hmyr;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;


public class db {
    static String username="root";
    static String password="hum1234";
    static String dbUrl ="jdbc:mysql://localhost:3306/oopgruppenprojekt?useSSL=false";
   
    public Connection getConnection() throws SQLException{
        return(Connection) DriverManager.getConnection(dbUrl, username, password);
    }
    
    public void ShowError(SQLException exception){
      System.out.println("Error :" + exception.getMessage());
      System.out.println("Error Code:" + exception.getErrorCode());  
    }
    
}
