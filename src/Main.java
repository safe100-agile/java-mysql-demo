import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mytestdb";
        String user = "root";
        String password = "Monday12$";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                System.out.println("MySQL version: " + rs.getString(1));
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(" MySQL connection error: " + e.getMessage());
        }
    }
}
