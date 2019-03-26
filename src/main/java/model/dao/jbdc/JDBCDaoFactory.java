package model.dao.jbdc;

import model.dao.*;
import model.dao.entity.City;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao();
    }

    @Override
    public CityDao createCityDao() {
        return new JDBCCityDao();
    }

    @Override
    public CityDistanceDao createCityDistanceDao() {
        return new JDBCCityDistanceDao() {
        };
    }

    @Override
    public DiscountDao createDiscountDao() {
        return new JDBCDiscountDao();
    }


}
