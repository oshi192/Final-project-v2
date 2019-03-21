package model.dao.jbdc;

import model.dao.CityDistanceDao;
import model.dao.entity.CityDistance;
import model.dao.jbdc.mapper.CityDistanceMapper;
import util.ResourceBundleManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCCityDistanceDao implements CityDistanceDao {
    private CityDistanceMapper mapper = new CityDistanceMapper();

    @Override
    public CityDistanceDao create(CityDistanceDao entity) {
        return null;
    }

    @Override
    public CityDistanceDao findById(int id) {
        return null;
    }

    @Override
    public List<CityDistanceDao> findAll() {
        return null;
    }

    @Override
    public void update(CityDistanceDao entity) {

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

    @Override
    public CityDistance findByCityIds(int fromCityId, int toCityId) {
        CityDistance cityDistance = null;
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.CITYDISTANCE_BY_IDS);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            st.setInt(1,fromCityId);
            st.setInt(2,toCityId);
            cityDistance = mapper.extractFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityDistance;
    }
}
