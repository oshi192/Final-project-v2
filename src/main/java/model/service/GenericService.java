package model.service;

import java.util.List;

public interface GenericService<T> {
    List<T> find(int currentPage, int recordsPerPage);
    int getNumberOfRows();
}
