package model.service;

import model.dao.entity.Order;

import java.util.List;

public class OrderService implements GenericService<Order> {
    @Override
    public List<Order> find(int currentPage, int recordsPerPage) {
        return null;
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }
}
