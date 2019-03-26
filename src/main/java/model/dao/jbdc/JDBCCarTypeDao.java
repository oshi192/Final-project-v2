package model.dao.jbdc;

import model.dao.CarTypeDao;
import model.dao.entity.CarType;
import model.dao.jbdc.mapper.CarTypeMapper;
import model.dao.jbdc.mapper.TaxiMapper;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCarTypeDao implements CarTypeDao {
    static final Logger logger = Logger.getLogger(JDBCCarTypeDao.class);
    CarTypeMapper mapper = new CarTypeMapper();

    @Override
    public CarType create(CarType entity) {
        final String query = ResourceBundleManager.getSqlString(ResourceBundleManager.CARTYPE_CREATE);
        try {
            Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
            try {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                mapper.putIntoPrepareStatement(ps, entity);
                ps.executeUpdate();
                connection.commit();
                logger.info("create successful");
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(e);//todo
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public CarType findById(int id) {
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.CARTYPE_BY_ID);
        logger.info(query);
        CarType carType = null;
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            logger.info(connection);
            st.setInt(1,id);
            ResultSet resultSet = st.executeQuery();
            logger.info(st);
            if (resultSet.next()) {
                carType=carTypeMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        logger.info("find by id-" + carType);
        return carType;
    }

    @Override
    public List<CarType> findAll() {
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.CARTYPE_ALL);
        logger.info(query);
        List<CarType> carTypes = new ArrayList<>();
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            logger.info(connection);
            ResultSet resultSet = st.executeQuery();
            logger.info(st);
            while (resultSet.next()) {
                carTypes.add(carTypeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        logger.info("findAll : count-" + carTypes.size());
        return carTypes;
    }

    @Override
    public void update(CarType entity) {

    }

    @Override
    public void delete(int id) {
        final String queryInsertTaxi = ResourceBundleManager.getSqlString(ResourceBundleManager.CARTYPE_DELETE);
        try {
            Connection connection = ConnectionPoolHolder.getDataSource().getConnection();

            try {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(queryInsertTaxi, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
                logger.info("delete successful");
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("cant get connection");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public int count() {
        int result = 0;
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.CARTYPE_COUNT);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CarType findByCarTypeName(String carTypeName) {
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.CARTYPE_BY_CARTYPENAME);
        logger.info(query);
        CarType carType = null;
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            logger.info(connection);
            st.setString(1, carTypeName);
            logger.info(st);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                carType = carTypeMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        logger.info("find carType-" + carType);
        return carType;
    }
}
