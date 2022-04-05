import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetaDate {
    private static final String userName = "root";
    private static final String password = "kerimovikh95";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM authors";
            ResultSet resultSet = statement.executeQuery(sql);
            List<AuthorBook> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(new AuthorBook(
                        resultSet.getInt("a_id"),
                        resultSet.getString("a_name")
                ));
            }
            authors.forEach(System.out::println);
            System.out.println();

            // MetaData
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnCount());
            System.out.println(resultSetMetaData.getColumnName(2));
            System.out.println(resultSetMetaData.getColumnType(2));
            System.out.println(resultSetMetaData.getColumnTypeName(2));
            System.out.println(resultSetMetaData.isNullable(2));

            // DB MetaData
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            System.out.println(databaseMetaData.getDatabaseProductName());
            System.out.println(databaseMetaData.getDatabaseProductVersion());
            System.out.println(databaseMetaData.getUserName());
            System.out.println(databaseMetaData.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
