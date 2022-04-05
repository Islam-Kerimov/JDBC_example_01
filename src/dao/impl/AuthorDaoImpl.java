package dao.impl;

import dao.AuthorDao;
import dao.DaoException;
import entity.AuthorBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private static final String userName = "root";
    private static final String password = "kerimovikh95";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    private static final String SQL_SELECT_ALL_AUTHORS = "SELECT a_id, a_name FROM authors";
    private static final String SQL_SELECT_AUTHOR_BY_LASTNAME = "SELECT a_id FROM authors WHERE a_name = ?";

    @Override
    public List<AuthorBook> findAuthorByLastName(String patternName) throws DaoException {
        List<AuthorBook> authors = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.prepareStatement(SQL_SELECT_AUTHOR_BY_LASTNAME);
            statement.setString(1, patternName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AuthorBook author = new AuthorBook();
                author.setId(resultSet.getInt("a_id"));
                author.setName(resultSet.getString("a_name"));
                authors.add(author);
            }
        } catch (SQLException e) {
//            System.err.println("SQLException: " + e);
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return authors;
    }

    @Override
    public List<AuthorBook> findAll() {
        List<AuthorBook> authors = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_AUTHORS);
            while (resultSet.next()) {
                AuthorBook author = new AuthorBook();
                author.setId(resultSet.getInt("a_id"));
                author.setName(resultSet.getString("a_name"));
                authors.add(author);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return authors;
    }

    @Override
    public AuthorBook findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(AuthorBook authorBook) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(AuthorBook authorBook) {
        return false;
    }

    @Override
    public AuthorBook update(AuthorBook authorBook) {
        return null;
    }
}
