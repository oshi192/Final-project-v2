package model.dao.jbdc.mapper;

import model.dao.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CityMapper implements ObjectMapper<City>{
    @Override
    public City extractFromResultSet(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt("idcity"));
        city.setCityName(resultSet.getString("city_name"));
        return city;
    }

    @Override
    public City makeUnique(Map<Integer, City> cache, City user) {
        return null;
    }
}
