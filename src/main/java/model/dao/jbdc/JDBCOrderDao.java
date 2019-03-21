package model.dao.jbdc;

import model.dao.OrderDao;
import model.dao.entity.Order;
import model.dao.jbdc.mapper.OrderMapper;
import model.dao.jbdc.mapper.TaxiMapper;
import model.dto.OrderDTO;
import model.dto.TaxiDTO;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    static final Logger logger = Logger.getLogger(JDBCOrderDao.class);
    private static OrderMapper mapper = new OrderMapper();

    @Override
    public Order create(Order entity) {
        final String queryInsert = ResourceBundleManager.getSqlString(ResourceBundleManager.ORDER_CREATE);
        try {
            Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
            try {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
                mapper.putIntoPrepareStatement(ps, entity);
                ps.executeUpdate();
                connection.commit();
                logger.info("order created");
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public int count() {
        int result = 0;
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.ORDER_COUNT);
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
    public List<Order> findAllByUserId(int userId) {
        logger.info("findAllOrders by uerId:" + userId);
        List<Order> orders = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.ORDER_ALL_BY_USER_ID);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, userId);
            ResultSet resultSet = st.executeQuery();
            logger.info(st);
            while (resultSet.next()) {
                orders.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("cant get connection");
        }
        logger.info("orders by user id: count-" + orders.size());
        return orders;
    }


    public List<Order> findAllByUserIdPaginate(int userId,int currentPage, int recordsPerPage) {
        logger.info("findAllOrders by uerId:" + userId);
        List<Order> orders = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.ORDER_ALL_BY_USER_ID_PAGGINATE);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, userId);
            st.setInt(2, (currentPage - 1) * recordsPerPage);
            st.setInt(3, recordsPerPage);
            ResultSet resultSet = st.executeQuery();
            logger.info(st);
            while (resultSet.next()) {
                orders.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("cant get connection");
        }
        logger.info("orders by user id: count-" + orders.size());
        return orders;
    }

    public List<OrderDTO> findAllByUserIdPaginateDTO(int userId, int currentPage, int recordsPerPage) {
        logger.info("findAllOrders by uerId:" + userId);
        List<OrderDTO> orders = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.ORDER_ALL_BY_USER_ID_PAGGINATE_JOIN);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, userId);
            st.setInt(2, (currentPage - 1) * recordsPerPage);
            st.setInt(3, recordsPerPage);
            ResultSet resultSet = st.executeQuery();
            logger.info(st);
            while (resultSet.next()) {
                orders.add(mapper.extractFromResultSetDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("cant get connection");
        }
        logger.info("orders by user id: count-" + orders.size());
        return orders;
    }
}