import java.sql.*;

public class Conn {

     public Connection c;  
    public Statement s;   

    public Conn() {
        try {
            // Load MySQL driver (recommended for compatibility)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank-management-system", "root", "");

            // Create statement
            s = c.createStatement();

            System.out.println("Database Connected Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
