package model.service;

import model.dao.DaoFactory;
import model.dao.TaxiDao;
import model.dao.UserDao;
import model.dao.entity.Taxi;
import model.dao.jbdc.JDBCTaxiDao;
import model.dao.jbdc.mapper.TaxiMapper;
import model.dto.TaxiDTO;
import util.ResourceBundleManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaxiService implements GenericService<TaxiDTO> {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<TaxiDTO> find(int currentPage, int recordsPerPage) {
        JDBCTaxiDao dao = (JDBCTaxiDao)daoFactory.createTaxiDao();
        List<TaxiDTO> taxis = dao.findAllDTO(currentPage,recordsPerPage);

        return taxis;
    }

    @Override
    public int getNumberOfRows() {
        TaxiDao dao = daoFactory.createTaxiDao();
        return dao.count();
    }
}
