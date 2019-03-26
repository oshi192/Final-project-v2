package model.dao.jbdc.mapper;

import model.dao.entity.CarType;
import model.dao.entity.Discount;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class DiscountMapper implements ObjectMapper<Discount> {
    @Override
    public Discount extractFromResultSet(ResultSet resultSet) throws SQLException {
        Discount discount = new Discount();
        discount.setAuthorId(resultSet.getInt("author_id"));
        discount.setText_uk(resultSet.getString("text_uk"));
        discount.setText(resultSet.getString("text"));
        discount.setSumWithUserDisc(resultSet.getBoolean("sum_with_usersDiscount"));
        discount.setStartTime(LocalDate.parse(resultSet.getString("startdate")));
        discount.setEndTime(LocalDate.parse(resultSet.getString("enddate")));
        discount.setId(resultSet.getInt("iddiscounts"));
        return discount;
    }
    public void putIntoPrepareStatement(PreparedStatement ps, Discount discount) throws SQLException {
        ps.setBoolean(1, discount.getIsSumWithUserDisc());
        ps.setString(2, discount.getText());
        ps.setString(3, discount.getText_uk());
        ps.setDate(4, Date.valueOf(discount.getStartTime()));
        ps.setDate(5, Date.valueOf(discount.getEndTime()));
        ps.setInt(6, discount.getAuthorId());
    }

    @Override
    public Discount makeUnique(Map<Integer, Discount> cache, Discount user) {
        return null;
    }
}
