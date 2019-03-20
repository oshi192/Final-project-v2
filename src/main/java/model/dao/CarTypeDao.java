package model.dao;

import model.dao.entity.CarType;

public interface CarTypeDao extends GenericDao<CarType> {
    CarType findByCarTypeName(String carType);
}
