package model.dao.jbdc;

import model.dao.CityDao;
import model.dao.entity.City;
import model.dao.entity.Taxi;
import model.dao.jbdc.mapper.CityMapper;
import util.ResourceBundleManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCCityDao implements CityDao {
    private CityMapper mapper = new CityMapper();
    @Override
    public City create(City entity) {
        return null;
    }

    @Override
    public City findById(int id) {
        return null;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.CITY_ALL);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                cities.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public void update(City entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public int count() {
        return 0;
    }
}
