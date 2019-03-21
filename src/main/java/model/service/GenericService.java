package model.service;

import model.dao.DaoFactory;

import java.util.List;

public interface GenericService<T> {
    DaoFactory daoFactory = DaoFactory.getInstance();

    List<T> find(int currentPage, int recordsPerPage);
    int getNumberOfRows();
}
