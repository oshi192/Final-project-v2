package model.service;

import model.dao.DaoFactory;
import model.dao.DiscountDao;
import model.dao.entity.Discount;
import model.dao.jbdc.JDBCDaoFactory;

import java.time.LocalDate;
import java.util.List;

public class DiscountService implements GenericService<Discount>{
    DaoFactory dao = JDBCDaoFactory.getInstance();
    @Override
    public List<Discount> find(int currentPage, int recordsPerPage) {

        return  dao.createDiscountDao().findAll();
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }

    public void create(Discount discount) {
        dao.createDiscountDao().create(discount);
    }

    public void deleteById(int id) {
        dao.createDiscountDao().delete(id);

    }

    public Discount findAllByEndDate() {
    return dao.createDiscountDao().findByDate(LocalDate.now());
    }
}
