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

        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            String sql = "SELECT a_id, a_name FROM authors";
            ResultSet resultSet = statement.executeQuery(sql);

            List<AuthorBook> authors = new ArrayList<>();

            // insert row
            resultSet.moveToInsertRow();
            resultSet.updateInt("a_id", 8);
            resultSet.updateString("a_name", "Islam Kerimov");
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            while (resultSet.next()) {
                // update row
                int id = resultSet.getInt("a_id");
                if (id == 4) {
                    resultSet.updateString("a_name", "777");
                    resultSet.updateRow();
                }
                String name = resultSet.getString("a_name");
                authors.add(new AuthorBook(id, name));
            }
            authors.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
