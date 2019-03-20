package model.dao;

import model.dao.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findAllByUserId(int userId);
}