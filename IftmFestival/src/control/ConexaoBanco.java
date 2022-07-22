package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBanco {

    public Connection Conecta() {
         try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "#4dmin_r007");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "UTC");
            properties.setProperty("allowPublicKeyRetrieval","true");
            
            String con = "jdbc:mysql://localhost:3306/mydb";
            return DriverManager.getConnection(con, properties);
            
        } catch (SQLException e) {
             System.out.println("ERRO AO CONECTAR.");
            throw new RuntimeException(e);
        }
    }
}
