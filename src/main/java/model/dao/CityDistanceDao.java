package model.dao;

import model.dao.entity.CityDistance;

public interface CityDistanceDao extends GenericDao<CityDistanceDao> {
    CityDistance findByCityIds(int fromCityId, int toCityId);
}
