package model.dao.jbdc;

import model.dao.DiscountDao;
import model.dao.entity.Discount;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class JDBCDiscountDao implements DiscountDao {
    private Connection connection;
    static final Logger logger = Logger.getLogger(JDBCDiscountDao.class);

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
        return null;
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
}