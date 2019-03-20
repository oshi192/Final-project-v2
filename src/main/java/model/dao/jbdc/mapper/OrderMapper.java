package model.dao.jbdc.mapper;

import model.dao.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("idorder"));
        order.setUserId(resultSet.getInt("user_iduser"));
        order.setCarTypeId(resultSet.getInt("carType_idCarType"));
        order.setOrderStatusId(resultSet.getInt("orderStatus_idorderStatus"));
        order.setTaxiId(resultSet.getInt("taxi_idtaxi"));
        order.setStartPoint(resultSet.getString("startPoint"));
        order.setEndPoint(resultSet.getString("endPoint"));
        order.setOrderTime(resultSet.getString("order_time"));
        order.setComment(resultSet.getString("comment"));

        return order;
    }

    @Override
    public Order makeUnique(Map<Integer, Order> cache, Order user) {
        return null;
    }
}
