package model.dao;

import model.dao.jbdc.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract CarTypeDao createCarTypeDao();
    public abstract TaxiDao createTaxiDao();
    public abstract OrderDao createOrderDao();
    public abstract CityDao createCityDao();
    public abstract CityDistanceDao createCityDistanceDao();

    public static DaoFactory getInstance() {
        if(daoFactory == null) {
            synchronized (DaoFactory.class) {
                if(daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

}
