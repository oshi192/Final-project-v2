package model.dao.jbdc.mapper;

import model.dao.entity.CityDistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CityDistanceMapper implements ObjectMapper<CityDistance> {
    @Override
    public CityDistance extractFromResultSet(ResultSet resultSet) throws SQLException {
        CityDistance cityDistance = new CityDistance();
        cityDistance.setDistanceKm(resultSet.getInt("distance_km"));
        cityDistance.setId(resultSet.getInt("idcity_distance"));
        cityDistance.setFromCityId(resultSet.getInt("fromcity_idcity1"));
        cityDistance.setToCityId(resultSet.getInt("tocity_idcity"));
        return cityDistance;
    }

    @Override
    public CityDistance makeUnique(Map<Integer, CityDistance> cache, CityDistance user) {
        return null;
    }
}
