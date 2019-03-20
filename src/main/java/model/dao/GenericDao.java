package model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    T create(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
    int count();
}
