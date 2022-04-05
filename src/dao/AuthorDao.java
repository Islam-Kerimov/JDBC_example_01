package dao;

import entity.AuthorBook;

import java.util.List;

public interface AuthorDao extends BaseDao<Long, AuthorBook> {
    List<AuthorBook> findAuthorByLastName(String patternName) throws DaoException;
}
