package model.dao.jbdc.mapper;

import model.dao.entity.Order;
import model.dao.entity.User;
import model.dto.OrderDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {
    /*dorder` INT NOT NULL AUTO_INCREMENT,
 `order_time` VARCHAR(45) NOT NULL,
 `user_iduser` INT NOT NULL,
 `carType_idCarType` INT NOT NULL,
 `comment` VARCHAR(255) NULL,
 `orderStatus_idorderStatus` INT NOT NULL,
 `taxi_idtaxi` INT NOT NULL,
 `city_distance_idcity_distance` INT NOT NULL,"*/
    @Override
    public Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("idorder"));
        order.setUserId(resultSet.getInt("user_iduser"));
        order.setCarTypeId(resultSet.getInt("carType_idCarType"));
        order.setOrderStatusId(resultSet.getInt("orderStatus_idorderStatus"));
        order.setTaxiId(resultSet.getInt("taxi_idtaxi"));
        order.setTime( LocalDateTime.parse(resultSet.getString("order_time")));
        order.setCityDistanceId( resultSet.getInt("city_distance_idcity_distance"));
        order.setComment(resultSet.getString("comment"));
        return order;
    }

    public OrderDTO extractFromResultSetDTO(ResultSet resultSet) throws SQLException {
        OrderDTO orderDto = (OrderDTO) extractFromResultSet(resultSet);
        orderDto.setCarType(new CarTypeMapper().extractFromResultSet(resultSet));
        User user = new UserMapper().extractFromResultSet(resultSet);
        user.setPassword("");
        orderDto.setUser(user);
        orderDto.setTaxi(new TaxiMapper().extractFromResultSet(resultSet));
        orderDto.setCityDistance(new CityDistanceMapper().extractFromResultSet(resultSet));

        return orderDto;
    }

    @Override
    public Order makeUnique(Map<Integer, Order> cache, Order user) {
        return null;
    }

    public void putIntoPrepareStatement(PreparedStatement ps, Order entity) throws SQLException {
        ps.setString(1,entity.getTime().toString());
        ps.setInt(2,entity.getUserId());
        ps.setInt(3,entity.getCarTypeId());
        ps.setString(4,entity.getComment());
        ps.setInt(5,entity.getOrderStatusId());
        ps.setInt(6,entity.getTaxiId());
        ps.setInt(7,entity.getCityDistanceId());

    }
}
