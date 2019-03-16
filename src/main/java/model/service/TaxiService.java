package model.service;

import model.dao.entity.Taxi;
import model.dao.jbdc.mapper.TaxiMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaxiService implements GenericService<Taxi> {
    private Connection connection;

    public TaxiService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Taxi> find(int currentPage, int recordsPerPage) {
        List<Taxi> taxis = null;

        int start = currentPage * recordsPerPage - recordsPerPage;
        String query = "SELECT * FROM taxi LIMIT ?, ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            TaxiMapper taxiMapper = new TaxiMapper();
            while (resultSet.next()) {
                taxis.add(taxiMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taxis;
    }

    @Override
    public int getNumberOfRows() {
        int numOfRows = 0;
        String query = "SELECT COUNT(Id) as count FROM Countries";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();
            numOfRows= resultSet.getInt("count");
        } catch (SQLException ex) {

        }

        return numOfRows;
    }
}
