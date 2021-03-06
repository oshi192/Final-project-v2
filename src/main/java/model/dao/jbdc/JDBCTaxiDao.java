package model.dao.jbdc;

import exception.UserAlreadyExistException;
import model.dao.TaxiDao;
import model.dao.entity.Taxi;
import model.dao.jbdc.mapper.TaxiMapper;
import model.dao.jbdc.mapper.UserMapper;
import model.dto.TaxiDTO;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTaxiDao implements TaxiDao {
    static final Logger logger = Logger.getLogger(JDBCTaxiDao.class);
    private TaxiMapper mapper = new TaxiMapper();


    public JDBCTaxiDao() {

    }


    @Override
    public Taxi create(Taxi entity) {
        final String queryInsertTaxi = ResourceBundleManager.getSqlString(ResourceBundleManager.TAXI_CREATE);
        try {
            Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
            try {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(queryInsertTaxi, Statement.RETURN_GENERATED_KEYS);
                new TaxiMapper().putIntoPrepareStatement(ps, entity);
                ps.executeUpdate();
                connection.commit();
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
    public Taxi findById(int id) {
        final String query = ResourceBundleManager.getSqlString(ResourceBundleManager.TAXI_BY_ID);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
                Taxi  taxi = new Taxi();
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    taxi = mapper.extractFromResultSet(rs);
                }
            return taxi;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Taxi> findAll() {
        List<Taxi> taxis = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.TAXI_ALL);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                taxis.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taxis;
    }

    public List<TaxiDTO> findAllDTO(int currentPage, int recordsPerPage) {
        logger.info("findAllDTO");
        List<TaxiDTO> taxis = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.TAXI_ALL_JOIN);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, (currentPage - 1) * recordsPerPage);
            st.setInt(2, recordsPerPage);
            ResultSet resultSet = st.executeQuery();
            logger.info(st);
            while (resultSet.next()) {
                taxis.add(mapper.extractFromResultSetDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("cant get connection");
        }
        logger.info("taxi all dto : count-" + taxis.size());
        return taxis;
    }

    @Override
    public void update(Taxi entity) {

    }

    @Override
    public void delete(int id) {
        final String query = ResourceBundleManager.getSqlString("taxi-delete");
        try {
            Connection connection = ConnectionPoolHolder.getDataSource().getConnection();

            try {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
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
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.TAXI_COUNT);
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
    public Taxi findByStatus(int status) {
        logger.info("find by status: "+status);
        Taxi taxi=null;
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.TAXI_BY_STATUS);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, status);
            ResultSet resultSet = st.executeQuery();
            logger.info(st);
            if(resultSet.next()) {
                taxi = mapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        logger.info("taxi" + taxi);
        return taxi;
    }
}