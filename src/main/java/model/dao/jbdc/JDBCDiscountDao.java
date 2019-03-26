package model.dao.jbdc;

import model.dao.DiscountDao;
import model.dao.entity.Discount;
import model.dao.jbdc.mapper.DiscountMapper;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCDiscountDao implements DiscountDao {
    static final Logger logger = Logger.getLogger(JDBCDiscountDao.class);
    private DiscountMapper mapper = new DiscountMapper();

    @Override
    public Discount create(Discount entity) {
        final String query = ResourceBundleManager.getSqlString(ResourceBundleManager.DISCOUNT_CREATE);
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
    public Discount findById(int id) {
        return null;
    }

    @Override
    public List<Discount> findAll() {
        logger.info("find - all");
        List<Discount> discounts = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.DISCOUNT_ALL);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                discounts.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("find - all : " + discounts.size()+discounts);
        return discounts;
    }

    @Override
    public void update(Discount entity) {

    }

    @Override
    public void delete(int id) {
        final String query = ResourceBundleManager.getSqlString(ResourceBundleManager.DISCOUNT_DELETE);
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
        } catch (SQLException e) {
            throw new RuntimeException("cant get connection");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public int count() {
        int result = 0;
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.DISCOUNT_COUNT);
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
    public Discount findByDate(LocalDate date) {
        logger.info("find - all");
        Discount discount =null;
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.DISCOUNT_BY_TIME);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1,date.toString());
            st.setString(2,date.toString());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                discount=mapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("find - date : " +discount);
        return discount;
    }
}