package model.dao.jbdc;

import model.dao.OrderDao;
import model.dao.entity.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;
    static final Logger logger = Logger.getLogger(JDBCOrderDao.class);

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Order create(Order entity) {
        return null;
    }

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
}