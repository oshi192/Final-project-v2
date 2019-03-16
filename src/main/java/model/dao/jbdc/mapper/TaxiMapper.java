package model.dao.jbdc.mapper;

import model.dao.entity.Role;
import model.dao.entity.Taxi;
import model.dao.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TaxiMapper  implements ObjectMapper<Taxi> {
    @Override
    public Taxi extractFromResultSet(ResultSet resultSet) throws SQLException {
        Taxi user = new Taxi();
        user.setId(resultSet.getInt("iduser"));
        user.setPhoneNumber(resultSet.getString("phone"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setSum(resultSet.getInt("summ"));
        user.setRole(new Role(resultSet.getInt("idrole"),(resultSet.getString("role"))));

        return user;
    }

    @Override
    public Taxi makeUnique(Map<Integer, Taxi> cache, Taxi user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
    public void putIntoPrepareStatement(PreparedStatement ps, Taxi user) throws SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getPhoneNumber());
        ps.setInt(6, user.getRole().getId());
    }
}