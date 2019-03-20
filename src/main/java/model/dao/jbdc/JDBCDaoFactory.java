package model.dao.jbdc;

import model.dao.CarTypeDao;
import model.dao.DaoFactory;
import model.dao.TaxiDao;
import model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao();
    }

    @Override
    public CarTypeDao createCarTypeDao() {
        return new JDBCCarTypeDao();
    }

    @Override
    public TaxiDao createTaxiDao() {
        return new JDBCTaxiDao();
    }

}
