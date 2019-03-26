package model.dao;

import model.dao.entity.Discount;

import java.time.LocalDate;

public interface DiscountDao extends GenericDao<Discount> {
    Discount findByDate(LocalDate plusDays);
}