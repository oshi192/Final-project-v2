package model.dao.jbdc.mapper;

import model.dao.entity.*;
import model.dto.TaxiDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TaxiMapper  implements ObjectMapper<Taxi> {
    @Override
    public Taxi extractFromResultSet(ResultSet resultSet) throws SQLException {
        Taxi taxi = new Taxi();
        taxi.setId(resultSet.getInt("idtaxi"));
        taxi.setIdcarType(resultSet.getInt("carType_idCarType"));
        taxi.setIdtaxiStatus(resultSet.getInt("status_idstatus"));
        taxi.setDescription(resultSet.getString("description"));

        return taxi;
    }
    public TaxiDTO extractFromResultSetDTO(ResultSet resultSet) throws SQLException {
        TaxiDTO taxi = new TaxiDTO();
        taxi.setId(resultSet.getInt("idtaxi"));
        taxi.setIdcarType(resultSet.getInt("carType_idCarType"));
        taxi.setIdtaxiStatus(resultSet.getInt("status_idstatus"));
        taxi.setDescription(resultSet.getString("description"));
        CarType carType = new CarType();
        carType.setId(resultSet.getInt("idCarType"));
        carType.setCarTypeName(resultSet.getString("carTypeName"));
        carType.setPriceCityKm(resultSet.getInt("price_city_km"));
        carType.setPriceOverTheCityKm(resultSet.getInt("price_over_the_city_km"));
        carType.setPriceWaitingTimeFree(resultSet.getInt("price_waiting_time_minute"));
        carType.setPriceWaitingTimeMinute(resultSet.getInt("price_waiting_time_free"));
        taxi.setCarType(carType);
        TaxiStatus taxiStatus = new TaxiStatus();
        taxiStatus.setId(resultSet.getInt("idTaxiStatus"));
        taxiStatus.setName(resultSet.getString("taxiStatusName"));
        taxi.setTaxiStatus(taxiStatus);
        return taxi;
    }

    @Override
    public Taxi makeUnique(Map<Integer, Taxi> cache, Taxi user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
    public void putIntoPrepareStatement(PreparedStatement ps, Taxi taxi) throws SQLException {
        ps.setInt(1, taxi.getIdcarType());
        ps.setString(2, taxi.getDescription());
    }
}