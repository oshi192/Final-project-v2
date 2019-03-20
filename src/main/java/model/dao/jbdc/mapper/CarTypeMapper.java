package model.dao.jbdc.mapper;

import model.dao.entity.CarType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CarTypeMapper implements ObjectMapper<CarType> {
    @Override
    public CarType extractFromResultSet(ResultSet resultSet) throws SQLException {
        CarType carType = new CarType();
        carType.setId(resultSet.getInt("idCarType"));
        carType.setCarTypeName(resultSet.getString("carTypeName"));
        carType.setPriceWaitingTimeMinute(resultSet.getInt("price_waiting_time_minute"));
        carType.setPriceWaitingTimeFree(resultSet.getInt("price_waiting_time_free"));
        carType.setPriceOverTheCityKm(resultSet.getInt("price_over_the_city_km"));
        carType.setPriceCityKm(resultSet.getInt("price_city_km"));
        return carType;
    }

    @Override
    public CarType makeUnique(Map<Integer, CarType> cache, CarType user) {
        return null;
    }
}
