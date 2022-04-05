import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class BatchMain {
    private static final String userName = "root";
    private static final String password = "kerimovikh95";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        properties.put("user", userName);
        properties.put("password", password);
        properties.put("autoReconnect", "true");
        properties.put("characterEncoding", "UTF-8");
        properties.put("useUnicode", "true");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, properties);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.addBatch("INSERT INTO authors VALUES (8, 'Islam Kerimov')");
            statement.addBatch("INSERT INTO books VALUES (8,  'My life. Tom 1', 2021, 10)");
            statement.addBatch("INSERT INTO books VALUES (9,'My life. Tom 2', 2022, 150)");
            int[] updateCounts = statement.executeBatch();
            connection.commit();
            System.out.println(Arrays.toString(updateCounts));
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try (Connection connection1 = DriverManager.getConnection(url, properties);
             Statement statement = connection1.createStatement()) {

            // select row authors
            String sqlAuthors = "SELECT * FROM authors";
            ResultSet resultSetForAuthors = statement.executeQuery(sqlAuthors);

            List<AuthorBook> authors = new ArrayList<>();
            while (resultSetForAuthors.next()) {
                authors.add(new AuthorBook(
                        resultSetForAuthors.getInt("a_id"),
                        resultSetForAuthors.getString("a_name")));
            }
            authors.forEach(System.out::println);

            // select rows books
            String sqlBook = "SELECT * FROM books";
            ResultSet resultSetForBooks = statement.executeQuery(sqlBook);

            List<Book> books = new ArrayList<>();
            while (resultSetForBooks.next()) {
                books.add(new Book(
                        resultSetForBooks.getInt("b_id"),
                        resultSetForBooks.getString("b_name"),
                        resultSetForBooks.getInt("b_year"),
                        resultSetForBooks.getInt("b_quantity")
                ));
            }
            books.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
