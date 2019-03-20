package model.dao.jbdc;

import model.dao.DiscountDao;
import model.dao.entity.Discount;
import model.dao.jbdc.mapper.DiscountMapper;
import org.apache.log4j.Logger;
import util.ResourceBundleManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDiscountDao implements DiscountDao {
    private Connection connection;
    static final Logger logger = Logger.getLogger(JDBCDiscountDao.class);
    private DiscountMapper mapper = new DiscountMapper();

    public JDBCDiscountDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Discount create(Discount entity) {
        return null;
    }

    @Override
    public Discount findById(int id) {
        return null;
    }

    @Override
    public List<Discount> findAll() {
        List<Discount> discounts = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(ResourceBundleManager.USER_ALL);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                discounts.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    @Override
    public void update(Discount entity) {

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
}