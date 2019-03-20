package model.dao.jbdc.mapper;

import model.dao.entity.Discount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DiscountMapper implements ObjectMapper<Discount> {
    @Override
    public Discount extractFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Discount makeUnique(Map<Integer, Discount> cache, Discount user) {
        return null;
    }
}
