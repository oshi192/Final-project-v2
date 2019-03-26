package model.dao.jdbc;

import model.dao.DaoFactory;
import model.dao.entity.Taxi;
import model.dao.jbdc.JDBCDaoFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JDBCTaxiDaoTest {
    private DaoFactory daoFactory = JDBCDaoFactory.getInstance();

    @Test
    public void getTaxiById() {
        Taxi taxi = daoFactory.createTaxiDao().findById(1);
        assertEquals(taxi.getIdcarType(),1);
    }
    @Test
    public void getAllTaxi(){
        assertTrue(daoFactory.createTaxiDao().findAll().size()>4);
    }

    @Test
    public void findByStatus(){
        assertEquals(daoFactory.createTaxiDao().findByStatus( 2).getId(),2);
    }

}
