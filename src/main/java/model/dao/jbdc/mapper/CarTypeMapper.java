package model.dao.jbdc.mapper;

import model.dao.entity.CarType;
import model.dao.entity.Taxi;

import java.sql.PreparedStatement;
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
    public CarType makeUnique(Map<Integer, CarType> cache, CarType carType) {
        return null;
    }

    public void putIntoPrepareStatement(PreparedStatement ps, CarType carType) throws SQLException {
        ps.setString(1, carType.getCarTypeName());
        ps.setInt(2, carType.getPriceCityKm());
        ps.setInt(3, carType.getPriceOverTheCityKm());
        ps.setInt(4, carType.getPriceWaitingTimeMinute());
        ps.setInt(5, carType.getPriceWaitingTimeFree());

    }
}
