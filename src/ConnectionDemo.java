import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionDemo {
    private static final String userName = "root";
    private static final String password = "kerimovikh95";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
//            System.out.println("We are connected");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Properties properties = new Properties();
        properties.put("user", userName);
        properties.put("password", password);
        properties.put("autoReconnect", "true");
        properties.put("characterEncoding", "UTF-8");
        properties.put("useUnicode", "true");

        try (Connection connection = DriverManager.getConnection(url, properties)) {
            String sql = "INSERT INTO authors(a_id, a_name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 9);
            statement.setString(2, "Erih Remark");
            int rowsUpdate = statement.executeUpdate();
            System.out.println(rowsUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
