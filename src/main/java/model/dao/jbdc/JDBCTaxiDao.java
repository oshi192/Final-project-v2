package model.dao.jbdc;

import model.dao.TaxiDao;
import model.dao.entity.Taxi;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class JDBCTaxiDao implements TaxiDao {
    private Connection connection;
    static final Logger logger = Logger.getLogger(JDBCTaxiDao.class);

    public JDBCTaxiDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Taxi create(Taxi entity) {
        return null;
    }

    @Override
    public Taxi findById(int id) {
        return null;
    }

    @Override
    public List<Taxi> findAll() {
        return null;
    }

    @Override
    public void update(Taxi entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}